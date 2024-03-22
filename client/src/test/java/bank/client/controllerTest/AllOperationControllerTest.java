package bank.client.controllerTest;

import bank.client.controller.AllOperationController;
import bank.client.representation.Operation;
import bank.client.representation.User;
import bank.client.service.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AllOperationControllerTest {

    @Mock
    private OperationService operationService;

    @InjectMocks
    private AllOperationController allOperationController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllOperations() throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
        when(operationService.getAllOperation(1L)).thenReturn(new ArrayList<>());
        List<Operation> operations = allOperationController.getAllOperations(user);
        assertEquals(0, operations.size());
    }

    @Test
    void testGetAllLimitOperations() throws JsonProcessingException {
        User user = new User();
        user.setUsername("test");
        when(operationService.getLimitOperation("test")).thenReturn(new ArrayList<>());
        List<User> users = allOperationController.getAllLimitOperations(user);
        assertEquals(0, users.size());
    }

    @Test
    void testGetAllLimitExceeded() throws JsonProcessingException {
        User user = new User();
        user.setUsername("test");
        when(operationService.getAllLimitExceededOperation("test")).thenReturn(new ArrayList<>());
        List<Operation> operations = allOperationController.getAllLimitExceeded(user);
        assertEquals(0, operations.size());
    }

    @Test
    void testSetNewLimit() {
        User user = new User();
        when(operationService.setNewLimit(user)).thenReturn(true);
        boolean result = allOperationController.setNewLimit(user);
        assertTrue(result);
    }
}

