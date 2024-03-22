package bank.server.entityTest;

import static org.junit.jupiter.api.Assertions.*;

import bank.server.entity.Currency;
import bank.server.entity.Operation;
import bank.server.entity.OperationType;
import bank.server.entity.User;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class UserTest {

    @Test
    void testToString() {
        User user = new User();
        user.setId(1L);
        user.setUsername("vasia");
        user.setProductLimits(BigDecimal.valueOf(500));
        user.setServiceLimits(BigDecimal.valueOf(700));
        user.setDate(new Date());

        String expected = "User(id=1, username=vasia, productLimits=500, serviceLimits=700, date=" + user.getDate() +
                ", operationList=null)";
        assertEquals(expected, user.toString());
    }

    @Test
    void testOperationList() {
        User user = new User();
        user.setId(1L);
        user.setUsername("vasia");
        user.setProductLimits(BigDecimal.valueOf(500));
        user.setServiceLimits(BigDecimal.valueOf(700));
        user.setDate(new Date());

        Operation operation1 = new Operation();
        operation1.setId(1L);
        operation1.setValue(BigDecimal.valueOf(100));
        operation1.setCurrency(Currency.RUB);
        operation1.setDate(new Date());
        operation1.setType(OperationType.PRODUCT);
        operation1.setLimitExceeded(false);
        operation1.setUser(user);

        Operation operation2 = new Operation();
        operation2.setId(2L);
        operation2.setValue(BigDecimal.valueOf(200));
        operation2.setCurrency(Currency.RUB);
        operation2.setDate(new Date());
        operation2.setType(OperationType.SERVICE);
        operation2.setLimitExceeded(false);
        operation2.setUser(user);

        List<Operation> operationList = new ArrayList<>();
        operationList.add(operation1);
        operationList.add(operation2);

        user.setOperationList(operationList);

        assertEquals(2, user.getOperationList().size());
        assertTrue(user.getOperationList().contains(operation1));
        assertTrue(user.getOperationList().contains(operation2));
    }
}
