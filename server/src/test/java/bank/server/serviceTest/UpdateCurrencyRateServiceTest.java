package bank.server.serviceTest;

import bank.server.entity.Currency;
import bank.server.repository.CurrencyRateRepository;
import bank.server.service.UpdateCurrencyRateService;
import bank.server.table.CurrencyRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class UpdateCurrencyRateServiceTest {

    @Mock
    private CurrencyRateRepository currencyRateRepository;

    @InjectMocks
    private UpdateCurrencyRateService updateCurrencyRateService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdate_ExistingRate() {
        // Создаем входные данные для теста
        Map<String, String> closedPrices = new HashMap<>();
        closedPrices.put("KZT", "1.5");

        // Мокируем возвращаемое значение findByCurrency
        when(currencyRateRepository.findByCurrency(Currency.KZT)).thenReturn(Optional.of(new CurrencyRate()));

        // Вызываем метод, который мы хотим протестировать
        updateCurrencyRateService.update(closedPrices);

        // Проверяем, что метод save был вызван один раз
        verify(currencyRateRepository, times(1)).save(any());
    }

    @Test
    public void testUpdate_NewRate() {
        // Создаем входные данные для теста
        Map<String, String> closedPrices = new HashMap<>();
        closedPrices.put("RUB", "92.4");

        // Мокируем возвращаемое значение findByCurrency
        when(currencyRateRepository.findByCurrency(Currency.RUB)).thenReturn(Optional.empty());

        // Вызываем метод, который мы хотим протестировать
        updateCurrencyRateService.update(closedPrices);

        // Проверяем, что метод save был вызван один раз
        verify(currencyRateRepository, times(1)).save(any());
    }
}
