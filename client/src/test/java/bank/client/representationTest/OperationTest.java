package bank.client.representationTest;

import bank.client.representation.Currency;
import bank.client.representation.Operation;
import bank.client.representation.OperationType;
import bank.client.representation.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OperationTest {

    @Test
    void testToString() {
        Operation operation = new Operation();
        operation.setId(1L);
        operation.setValue(BigDecimal.valueOf(100));
        operation.setCurrency(Currency.RUB);
        operation.setDate(new Date());
        operation.setType(OperationType.SERVICE);
        operation.setLimitExceeded(false);
        User user = new User();
        user.setId(123L);
        operation.setUser(user);

        String expectedToString = "Operation{id=1, value=100, currency=RUB, date=" + operation.getDate() +
                ", type=SERVICE, limitExceeded=false, user=123}";
        assertEquals(expectedToString, operation.toString());
    }

    @Test
    void testGetterAndSetter() {
        Operation operation = new Operation();
        Long id = 1L;
        BigDecimal value = BigDecimal.valueOf(100);
        Currency currency = Currency.RUB;
        Date date = new Date();
        OperationType type = OperationType.SERVICE;
        boolean limitExceeded = false;
        User user = new User();
        user.setId(123L);

        operation.setId(id);
        operation.setValue(value);
        operation.setCurrency(currency);
        operation.setDate(date);
        operation.setType(type);
        operation.setLimitExceeded(limitExceeded);
        operation.setUser(user);

        assertEquals(id, operation.getId());
        assertEquals(value, operation.getValue());
        assertEquals(currency, operation.getCurrency());
        assertEquals(date, operation.getDate());
        assertEquals(type, operation.getType());
        assertEquals(limitExceeded, operation.isLimitExceeded());
        assertEquals(user, operation.getUser());
    }
}
