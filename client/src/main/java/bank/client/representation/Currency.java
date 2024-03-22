package bank.client.representation;

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
}
