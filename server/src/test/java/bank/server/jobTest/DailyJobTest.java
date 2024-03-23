package bank.server.jobTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import bank.server.job.DailyJob;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class DailyJobTest {

    @Test
    void testGetClosedPrices() {
        // Создаем фейковый ответ
        String fakeResponse = "{\"USD/RUB\":{\"meta\":{\"symbol\":\"USD/RUB\",\"interval\":\"1day\",\"currency_base\":\"USD\",\"currency_quote\":\"RUB\",\"type\":\"Stock\",\"exchange\":\"Forex\",\"timezone\":\"UTC\",\"valid_until\":\"2022-03-17 00:00:00\"},\"values\":[{\"datetime\":\"2022-03-17\",\"open\":\"82.520\",\"high\":\"82.810\",\"low\":\"82.360\",\"close\":92.40000,\"volume\":\"0\"}]},\"USD/KZT\":{\"meta\":{\"symbol\":\"USD/KZT\",\"interval\":\"1day\",\"currency_base\":\"USD\",\"currency_quote\":\"KZT\",\"type\":\"Stock\",\"exchange\":\"Forex\",\"timezone\":\"UTC\",\"valid_until\":\"2022-03-17 00:00:00\"},\"values\":[{\"datetime\":\"2022-03-17\",\"open\":\"432.700\",\"high\":\"432.700\",\"low\":\"432.700\",\"close\":432.700,\"volume\":\"0\"}]}}";

        // Создаем мок RestTemplate
        RestTemplate restTemplateMock = mock(RestTemplate.class);

        // Устанавливаем фейковый ответ при вызове restTemplate.getForEntity()
        when(restTemplateMock.getForEntity(anyString(), eq(String.class)))
                .thenReturn(new ResponseEntity<>(fakeResponse, HttpStatus.OK));

        // Создаем объект DailyJob и устанавливаем мок RestTemplate
        DailyJob dailyJob = new DailyJob();

        // Вызываем метод getClosedPrices()
        Map<String, String> closedPrices = dailyJob.getClosedPrices();

        // Проверяем, что метод вернул ожидаемые результаты
        assertEquals(2, closedPrices.size());

        assertEquals("\"92.13500\"", closedPrices.get("RUB"));
        assertEquals("\"450.40500\"", closedPrices.get("KZT"));
    }
}

