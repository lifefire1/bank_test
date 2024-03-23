package bank.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@ApiModel(description = "Enum representing a currency")
public enum Currency {
    @Schema(description = "Russian Ruble")
    RUB("Russian Ruble"),

    @Schema(description = "Kazakhstani Tenge")
    KZT("Kazakhstani Tenge");

    private final String displayName;

    Currency(String displayName) {
        this.displayName = displayName;
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
