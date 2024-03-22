package bank.server.contrllerTest;

import bank.server.controller.CurrencyController;
import bank.server.service.UpdateCurrencyRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyControllerTest {

    @Mock
    private UpdateCurrencyRateService updateCurrencyRateService;

    @InjectMocks
    private CurrencyController currencyController;

    @Test
    void testProcessClosedPrices() {
        // Создаем тестовые закрытые цены
        Map<String, String> closedPrices = new HashMap<>();
        closedPrices.put("RUB", "92.4");
        closedPrices.put("KZT", "450.001");

        // Ожидаемый результат
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Данные успешно получены и обработаны", HttpStatus.OK);

        // Вызываем метод контроллера
        ResponseEntity<String> actualResponse = currencyController.processClosedPrices(closedPrices);

        // Проверяем, что метод updateCurrencyRateService.update(closedPrices) был вызван один раз
        verify(updateCurrencyRateService, times(1)).update(closedPrices);

        // Проверяем, что возвращаемый ответ соответствует ожидаемому результату
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }
}
