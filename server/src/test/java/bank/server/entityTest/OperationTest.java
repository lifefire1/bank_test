package bank.server.entityTest;

import static org.junit.jupiter.api.Assertions.*;

import bank.server.entity.Currency;
import bank.server.entity.Operation;
import bank.server.entity.OperationType;
import bank.server.entity.User;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Date;

public class OperationTest {

    @Test
    void testToString() {
        User user = new User();
        user.setId(1L);

        Operation operation = new Operation();
        operation.setId(1L);
        operation.setValue(BigDecimal.valueOf(100));
        operation.setCurrency(Currency.RUB);
        operation.setDate(new Date());
        operation.setType(OperationType.PRODUCT);
        operation.setLimitExceeded(false);
        operation.setUser(user);

        String expected = "Operation{id=1, value=100, currency=RUB, date=" + operation.getDate() +
                ", type=PRODUCT, limitExceeded=false, user=1}";
        assertEquals(expected, operation.toString());
    }

    @Test
    void testHashCodeAndEquals() {
        Date shareDate = new Date();
        User shareUser = new User();
        // Создаем два эквивалентных объекта Operation
        Operation operation1 = new Operation();
        operation1.setId(1L);
        operation1.setValue(BigDecimal.valueOf(100));
        operation1.setCurrency(Currency.RUB);
        operation1.setDate(shareDate);
        operation1.setType(OperationType.PRODUCT);
        operation1.setLimitExceeded(false);
        operation1.setCurrentLimit(BigDecimal.valueOf(500));
        operation1.setUser(shareUser);

        Operation operation2 = new Operation();
        operation2.setId(1L);
        operation2.setValue(BigDecimal.valueOf(100));
        operation2.setCurrency(Currency.RUB);
        operation2.setDate(shareDate);
        operation2.setType(OperationType.PRODUCT);
        operation2.setLimitExceeded(false);
        operation2.setCurrentLimit(BigDecimal.valueOf(500));
        operation2.setUser(shareUser);

        // Проверяем, что хеш-коды для эквивалентных объектов равны
        assertEquals(operation1.hashCode(), operation2.hashCode());

        // Проверяем, что эквивалентные объекты считаются равными
        assertEquals(operation1, operation2);
    }

    @Test
    void testHashCode() {
        Operation operation1 = new Operation();
        Operation operation2 = new Operation();
        assertEquals(operation1.hashCode(), operation2.hashCode());
    }

    @Test
    void testEquals() {
        Operation operation1 = new Operation();
        Operation operation2 = new Operation();
        assertTrue(operation1.equals(operation2));
    }

    @Test
    void testSetValue() {
        Operation operation = new Operation();
        operation.setValue(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(100), operation.getValue());
    }

    @Test
    void testGetCurrency() {
        Operation operation = new Operation();
        operation.setCurrency(Currency.RUB);
        assertEquals(Currency.RUB, operation.getCurrency());
    }

    @Test
    void testGetType() {
        Operation operation = new Operation();
        operation.setType(OperationType.PRODUCT);
        assertEquals(OperationType.PRODUCT, operation.getType());
    }

    @Test
    void testSetCurrentLimit() {
        Operation operation = new Operation();
        operation.setCurrentLimit(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1000), operation.getCurrentLimit());
    }

    @Test
    void testGetUser() {
        User user = new User();
        Operation operation = new Operation();
        operation.setUser(user);
        assertEquals(user, operation.getUser());
    }
}
