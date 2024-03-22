package bank.server.tableTest;

import bank.server.entity.Currency;
import bank.server.repository.CurrencyRateRepository;
import bank.server.table.CurrencyRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest

public class CurrencyRateTest { @Autowired
private CurrencyRateRepository currencyRateRepository;

    @Test
    public void testSaveCurrencyRate() {
        // Создание объекта CurrencyRate
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCurrency(Currency.RUB);
        currencyRate.setRate(BigDecimal.valueOf(1.0));
        currencyRate.setTimestamp(System.currentTimeMillis());

        // Сохранение объекта в репозитории
        currencyRateRepository.save(currencyRate);

        // Получение объекта из репозитория
        CurrencyRate savedCurrencyRate = currencyRateRepository.findById(String.valueOf(currencyRate.getCurrency())).orElse(null);

        // Проверка, что объект успешно сохранен и извлечен из базы данных
        assertEquals(currencyRate.getCurrency(), savedCurrencyRate.getCurrency());
        assertEquals(currencyRate.getRate(), savedCurrencyRate.getRate());
        assertEquals(currencyRate.getTimestamp(), savedCurrencyRate.getTimestamp());
    }

    @Test
    void testEquals() {
        CurrencyRate rate1 = new CurrencyRate();
        rate1.setCurrency(Currency.RUB);
        rate1.setRate(BigDecimal.valueOf(80));

        CurrencyRate rate2 = new CurrencyRate();
        rate2.setCurrency(Currency.RUB);
        rate2.setRate(BigDecimal.valueOf(80));

        assertEquals(rate1, rate2);
    }

    @Test
    void testHashCode() {
        CurrencyRate rate1 = new CurrencyRate();
        rate1.setCurrency(Currency.RUB);
        rate1.setRate(BigDecimal.valueOf(80));

        CurrencyRate rate2 = new CurrencyRate();
        rate2.setCurrency(Currency.RUB);
        rate2.setRate(BigDecimal.valueOf(80));

        assertEquals(rate1.hashCode(), rate2.hashCode());
    }

    @Test
    void testToString() {
        CurrencyRate rate = new CurrencyRate();
        rate.setCurrency(Currency.RUB);
        rate.setRate(BigDecimal.valueOf(80));
        rate.setTimestamp(1648197588);

        String expected = "CurrencyRate(currency=RUB, rate=80, timestamp=1648197588)";
        assertEquals(expected, rate.toString());
    }
}
