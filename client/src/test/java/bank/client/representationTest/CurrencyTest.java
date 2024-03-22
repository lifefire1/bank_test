package bank.client.representationTest;

import bank.client.representation.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyTest {

    @Test
    void testGetDisplayName_RUB() {
        String expectedDisplayName = "Russian Ruble";
        String actualDisplayName = Currency.RUB.getDisplayName();
        assertEquals(expectedDisplayName, actualDisplayName);
    }

    @Test
    void testGetDisplayName_KZT() {
        String expectedDisplayName = "Kazakhstani Tenge";
        String actualDisplayName = Currency.KZT.getDisplayName();
        assertEquals(expectedDisplayName, actualDisplayName);
    }
}