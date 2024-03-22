package bank.server.entityTest;

import static org.junit.jupiter.api.Assertions.*;

import bank.server.entity.Currency;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CurrencyTest {

    @Test
    void testGetAllCurrencyCodes() {
        List<String> currencyCodes = Currency.getAllCurrencyCodes();
        assertNotNull(currencyCodes);
        assertEquals(2, currencyCodes.size());
        assertTrue(currencyCodes.contains("RUB"));
        assertTrue(currencyCodes.contains("KZT"));
    }

    @Test
    void testGetAllDisplayNames() {
        List<String> displayNames = Currency.getAllDisplayNames();
        assertNotNull(displayNames);
        assertEquals(2, displayNames.size());
        assertTrue(displayNames.contains("Russian Ruble"));
        assertTrue(displayNames.contains("Kazakhstani Tenge"));
    }
}
