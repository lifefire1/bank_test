package com.example.demo.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Currency {
    RUB("Russian Ruble"),
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
