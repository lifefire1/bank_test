package bank.server.serviceTest;

import bank.server.entity.*;
import bank.server.repository.CurrencyRateRepository;
import bank.server.repository.OperationRepository;
import bank.server.repository.UserRepository;
import bank.server.repository.UserSpendingRepository;
import bank.server.service.OperationService;
import bank.server.table.CurrencyRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OperationServiceTest {

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private UserSpendingRepository userSpendingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CurrencyRateRepository currencyRateRepository;

    @InjectMocks
    private OperationService operationService;

    @BeforeEach
    void setUp() {
        // Reset mock before each test
        reset(operationRepository);
        reset(userSpendingRepository);
        reset(userRepository);
        reset(currencyRateRepository);
    }

    @Test
    void testCheckOperation_Success() {
        Operation operation = new Operation();
        User user = new User();
        user.setId(1L);
        user.setUsername("vasia");
        operation.setUser(user);
        operation.setCurrency(Currency.RUB);
        operation.setValue(BigDecimal.valueOf(500));
        operation.setType(OperationType.SERVICE);

        UserSpending userSpending = new UserSpending();
        userSpending.setUser(user);
        userSpending.setCurrentSumService(BigDecimal.valueOf(1000));

        Optional<User> userOptional = Optional.of(user);
        Optional<UserSpending> userSpendingOptional = Optional.of(userSpending);
        Optional<CurrencyRate> currencyRateOptional = Optional.of(new CurrencyRate(Currency.RUB, BigDecimal.ONE, 0L));

        when(userRepository.findById(1L)).thenReturn(userOptional);
        when(userSpendingRepository.findByUserId(1L)).thenReturn(userSpendingOptional.get());
        when(userRepository.findFirstByUsernameOrderByDateDesc("vasia")).thenReturn(userOptional);
        when(currencyRateRepository.findByCurrency(Currency.RUB)).thenReturn(currencyRateOptional);

    }

    @Test
    void testCheckOperation_Failure() {
        Operation operation = new Operation();
        assertFalse(operationService.checkOperation(operation));
    }

    @Test
    void testSaveOperation() {
        Operation operation = new Operation();
        User user = new User();
        user.setId(1L);
        operation.setUser(user);
        operation.setCurrency(Currency.RUB);
        operation.setValue(BigDecimal.valueOf(500));
        operation.setType(OperationType.SERVICE);

        UserSpending userSpending = new UserSpending();
        userSpending.setUser(user);
        userSpending.setCurrentSumService(BigDecimal.valueOf(1000));

        Optional<UserSpending> userSpendingOptional = Optional.of(userSpending);

        when(userSpendingRepository.findByUserId(1L)).thenReturn(userSpendingOptional.get());
        when(currencyRateRepository.findByCurrency(Currency.RUB)).thenReturn(Optional.empty());

    }

    @Test
    void testCheckOperation_NullOperation() {
        assertFalse(operationService.checkOperation(null));
    }

    @Test
    void testCheckOperation_NullUser() {
        Operation operation = new Operation();
        assertFalse(operationService.checkOperation(operation));
    }

    @Test
    void testCheckOperation_NullCurrency() {
        Operation operation = new Operation();
        operation.setUser(new User());
        assertFalse(operationService.checkOperation(operation));
    }

    @Test
    void testCheckOperation_NegativeValue() {
        Operation operation = new Operation();
        operation.setUser(new User());
        operation.setCurrency(Currency.RUB);
        operation.setValue(BigDecimal.valueOf(-100));
        assertFalse(operationService.checkOperation(operation));
    }

    @Test
    void testCheckOperation_NullUserSpending() {
        Operation operation = new Operation();
        User user = new User();
        user.setId(1L);
        operation.setUser(user);
        operation.setCurrency(Currency.RUB);
        operation.setValue(BigDecimal.valueOf(100));
        when(userSpendingRepository.findByUserId(1L)).thenReturn(null);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findFirstByUsernameOrderByDateDesc("vasia")).thenReturn(Optional.of(user));
        assertFalse(operationService.checkOperation(operation));
    }

    @Test
    void testCheckOperation() {
        Operation operation = new Operation();
        User user = new User();
        user.setId(1L);
        user.setUsername("vasia");
        operation.setUser(user);
        operation.setCurrency(Currency.RUB);
        operation.setValue(BigDecimal.valueOf(100));
        UserSpending spending = new UserSpending();
        spending.setUser(user);
        spending.setCurrentSumProduct(new BigDecimal(0));
        spending.setCurrentSumProduct(new BigDecimal(0));
        when(userSpendingRepository.findByUserId(1L)).thenReturn(spending);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findFirstByUsernameOrderByDateDesc("vasia")).thenReturn(Optional.of(user));
        assertTrue(operationService.checkOperation(operation));
    }

    @Test
    void testCheckOperation_Limit() {
        Operation operation = new Operation();
        User user = new User();
        user.setId(1L);
        user.setUsername("vasia");
        operation.setUser(user);
        operation.setCurrency(Currency.RUB);
        operation.setType(OperationType.SERVICE);
        operation.setValue(BigDecimal.valueOf(1000000000));
        UserSpending spending = new UserSpending();
        spending.setUser(user);
        spending.setCurrentSumService(new BigDecimal(0));
        spending.setCurrentSumProduct(new BigDecimal(0));
        when(userSpendingRepository.findByUserId(1L)).thenReturn(spending);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findFirstByUsernameOrderByDateDesc("vasia")).thenReturn(Optional.of(user));
        CurrencyRate rate = new CurrencyRate();
        rate.setCurrency(Currency.RUB);
        rate.setRate(new BigDecimal(92.4));
        when(currencyRateRepository.findByCurrency(Currency.RUB)).thenReturn(Optional.of(rate));
        assertFalse(operationService.checkOperation(operation));
    }

    @Test
    void testCheckOperation_Limit_Product() {
        Operation operation = new Operation();
        User user = new User();
        user.setId(1L);
        user.setUsername("vasia");
        operation.setUser(user);
        operation.setCurrency(Currency.RUB);
        operation.setType(OperationType.PRODUCT);
        operation.setValue(BigDecimal.valueOf(1000000000));
        UserSpending spending = new UserSpending();
        spending.setUser(user);
        spending.setCurrentSumService(new BigDecimal(0));
        spending.setCurrentSumProduct(new BigDecimal(0));
        when(userSpendingRepository.findByUserId(1L)).thenReturn(spending);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findFirstByUsernameOrderByDateDesc("vasia")).thenReturn(Optional.of(user));
        CurrencyRate rate = new CurrencyRate();
        rate.setCurrency(Currency.RUB);
        rate.setRate(new BigDecimal(92.4));
        when(currencyRateRepository.findByCurrency(Currency.RUB)).thenReturn(Optional.of(rate));
        assertFalse(operationService.checkOperation(operation));
    }

    @Test
    void checkOperationTest(){
        Operation operation = null;
//        when(operationService.checkOperation(operation)).thenReturn(false);
        assertFalse(operationService.saveOperation(operation));
    }

    @Test
    void saveOperationTest_1() {
        OperationService operationServiceSpy = spy(operationService);
        Operation operation = new Operation();
        assertFalse(operationServiceSpy.saveOperation(operation));
    }

    @Test
    void saveOperationTest_2() {
        OperationService operationServiceSpy = spy(operationService);
        Operation operation = new Operation();
        operation.setLimitExceeded(true);
        assertFalse(operationServiceSpy.saveOperation(operation));
    }

    @Test
    void saveOperationTest_3() {
        Operation operation = new Operation();
        User user = new User();
        user.setUsername("v");
        user.setProductLimits(new BigDecimal(2000));
        user.setServiceLimits(new BigDecimal(2000));
        user.setId(1L);
        user.setDate(new Date());
        operation.setValue(new BigDecimal(100));
        operation.setLimitExceeded(false);
        operation.setDate(new Date());
        operation.setUser(user);
        operation.setCurrentLimit(new BigDecimal(1000));
        operation.setType(OperationType.SERVICE);
        operation.setCurrency(Currency.RUB);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserSpending spending = new UserSpending();
        spending.setUser(user);
        spending.setCurrentSumProduct(new BigDecimal(0));
        spending.setCurrentSumService(new BigDecimal(0));
        when(userSpendingRepository.findByUserId(1L)).thenReturn(spending);
        when(userRepository.findFirstByUsernameOrderByDateDesc(user.getUsername())).thenReturn(Optional.of(user));
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setRate(new BigDecimal(92.40));
        currencyRate.setCurrency(Currency.RUB);
        when(currencyRateRepository.findByCurrency(Currency.RUB)).thenReturn(Optional.of(currencyRate));
        assertTrue(operationService.saveOperation(operation));
    }
}