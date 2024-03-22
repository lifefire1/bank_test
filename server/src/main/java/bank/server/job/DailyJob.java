package bank.server.job;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import bank.server.entity.Currency;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DailyJob implements Job {

    private final RestTemplate restTemplate = new RestTemplate();

    private ObjectMapper objectMapper = new ObjectMapper();

    public DailyJob() {
    }

    public Map<String, String> getClosedPrices() {
        Map<String, String> closedPrices = new HashMap<>();
        String apikey = "edff0a6c884248c1970927f41d4ec174";
        StringBuilder url = new StringBuilder("https://api.twelvedata.com/time_series?symbol=");
        var currencyList = Currency.getAllCurrencyCodes();
        for (var elem : currencyList){
            url.append("USD/").append(elem).append(",");
        }
        url.replace(url.length()-1,url.length(),"");
        url.append("&interval=1day&apikey=").append(apikey).append("&source=docs&outputsize=1");
        log.info(url.toString());

        ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);

        log.info("Currency Rate: {}", response);

        String closePrice;
        try {
            for (var currency: currencyList){
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                JsonNode usdRubNode = rootNode.get("USD/" + currency);
                JsonNode valuesNode = usdRubNode.get("values");
                closePrice = valuesNode.get(0).get("close").toString();
                closedPrices.put(currency,closePrice);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return closedPrices;
    }

    public void sendClosedPricesToController() {
        Map<String, String> closedPrices = getClosedPrices();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Преобразуем HashMap в JSON
        String json;
        try {
            json = objectMapper.writeValueAsString(closedPrices);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Создаем тело запроса с нашим JSON
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        // Отправляем POST запрос на ваш контроллер
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/process-closed-prices", requestEntity, String.class);

        // Обрабатываем ответ, если это необходимо
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Данные успешно отправлены на контроллер");
        } else {
            System.out.println("Произошла ошибка при отправке данных на контроллер");
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        sendClosedPricesToController();
        //rateRepository.save(new CurrencyRate());

    }

}
