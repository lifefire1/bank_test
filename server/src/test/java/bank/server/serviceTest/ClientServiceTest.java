package bank.server.serviceTest;

import bank.server.entity.Operation;
import bank.server.entity.User;
import bank.server.repository.OperationRepository;
import bank.server.repository.UserRepository;
import bank.server.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        // Reset mock before each test
        reset(operationRepository);
        reset(userRepository);
    }

    @Test
    void testGetAllOperations() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Operation operation1 = new Operation();
        operation1.setId(1L);
        operation1.setUser(user);

        Operation operation2 = new Operation();
        operation2.setId(2L);
        operation2.setUser(user);

        List<Operation> operationList = new ArrayList<>();
        operationList.add(operation1);
        operationList.add(operation2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(operationRepository.findAllByUser(user)).thenReturn(operationList);

        List<Operation> result = clientService.getAllOperations(userId);
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllLimit() {
        String username = "vasia";

        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUsername(username);
        userList.add(user1);

        when(userRepository.findAllByUsernameOrderByDateDesc(username)).thenReturn(userList);

        List<User> result = clientService.getAllLimit(username);
        assertEquals(1, result.size());
    }

    @Test
    void testGetAlLimitExceededOperations() {
        String username = "vasia";
        User user = new User();
        user.setUsername(username);

        Operation operation1 = new Operation();
        operation1.setId(1L);
        operation1.setUser(user);
        operation1.setLimitExceeded(true);

        Operation operation2 = new Operation();
        operation2.setId(2L);
        operation2.setUser(user);
        operation2.setLimitExceeded(true);

        List<Operation> operationList = new ArrayList<>();
        operationList.add(operation1);
        operationList.add(operation2);

        when(userRepository.findFirstByUsername(username)).thenReturn(Optional.of(user));
        when(operationRepository.findAllByLimitExceededAndUser(true, user)).thenReturn(operationList);

        List<Operation> result = clientService.getAlLimitExceededOperations(username);
        assertEquals(2, result.size());
    }

    @Test
    void testSetNewLimit() {
        String username = "vasia";
        User user = new User();
        user.setUsername(username);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername(username);

        when(userRepository.findFirstByUsernameOrderByDateDesc(username)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(savedUser);

        boolean result = clientService.setNewLimit(user);
        assertEquals(true, result);
    }
}
