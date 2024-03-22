package bank.client.representationTest;

import bank.client.representation.Operation;
import bank.client.representation.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    void testGetterAndSetter() {
        User user = new User();
        Long id = 1L;
        String username = "testUser";
        Long productLimits = 500L;
        Long serviceLimits = 1000L;
        Date date = new Date();
        List<Operation> operationList = new ArrayList<>();

        user.setId(id);
        user.setUsername(username);
        user.setProductLimits(productLimits);
        user.setServiceLimits(serviceLimits);
        user.setDate(date);
        user.setOperationList(operationList);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(productLimits, user.getProductLimits());
        assertEquals(serviceLimits, user.getServiceLimits());
        assertEquals(date, user.getDate());
        assertEquals(operationList, user.getOperationList());
    }
}