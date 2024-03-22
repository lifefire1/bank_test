package bank.server.entityTest;

import static org.junit.jupiter.api.Assertions.*;

import bank.server.entity.User;
import bank.server.entity.UserSpending;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class UserSpendingTest {

    @Test
    void testToString() {
        User user = new User();
        user.setId(1L);

        UserSpending userSpending = new UserSpending();
        userSpending.setId(1L);
        userSpending.setUser(user);
        userSpending.setCurrentSumService(BigDecimal.valueOf(500));
        userSpending.setCurrentSumProduct(BigDecimal.valueOf(700));

        String expected = "UserSpending{id=1, user=1, currentSumService=500, currentSumProduct=700}";
        assertEquals(expected, userSpending.toString());
    }

    @Test
    void testEquals() {
        UserSpending userSpending1 = new UserSpending();
        UserSpending userSpending2 = new UserSpending();
        assertTrue(userSpending1.equals(userSpending2));
    }

    @Test
    void testHashCode() {
        UserSpending userSpending1 = new UserSpending();
        UserSpending userSpending2 = new UserSpending();
        assertEquals(userSpending1.hashCode(), userSpending2.hashCode());
    }

    @Test
    void testGetId() {
        UserSpending userSpending = new UserSpending();
        userSpending.setId(1L);
        assertEquals(1L, userSpending.getId());
    }

    @Test
    void testGetUser() {
        User user = new User();
        UserSpending userSpending = new UserSpending();
        userSpending.setUser(user);
        assertEquals(user, userSpending.getUser());
    }

    @Test
    void testGetCurrentSumService() {
        UserSpending userSpending = new UserSpending();
        userSpending.setCurrentSumService(BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(500), userSpending.getCurrentSumService());
    }

    @Test
    void testGetCurrentSumProduct() {
        UserSpending userSpending = new UserSpending();
        userSpending.setCurrentSumProduct(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1000), userSpending.getCurrentSumProduct());
    }
}