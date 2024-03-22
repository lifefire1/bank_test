package bank.server.entity;

import java.util.Arrays;
import java.util.List;

public enum Currency {
    RUB("Russian Ruble"),
    KZT("Kazakhstani Tenge");

    private final String displayName;

    Currency(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Метод для получения всех кодов валют
    public static List<String> getAllCurrencyCodes() {
        return Arrays.stream(values())
                .map(Enum::name)
                .toList();
    }

    // Метод для получения всех отображаемых имен валют
    public static List<String> getAllDisplayNames() {
        return Arrays.stream(values())
                .map(Currency::getDisplayName)
                .toList();
    }
}
