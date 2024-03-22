
package bank.client.service;

import bank.client.representation.Operation;
import bank.client.representation.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class OperationService {
    private final RestTemplate restTemplate;

    public OperationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Operation> getAllOperation(Long userId) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ObjectMapper objectMapper = new ObjectMapper();

        // Выполните запрос и получите ResponseEntity<String>
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8080/all_operations/retrieve?userId=" + userId,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        // Проверяем статус код ответа

        // Получите строку JSON из ResponseEntity
        String responseBody = responseEntity.getBody();

        // Преобразуйте JSON в список объектов типа Operation
        List<Operation> operations = objectMapper.readValue(responseBody, new TypeReference<List<Operation>>() {
        });

        // Теперь у вас есть список операций для использования
        log.info("operations is: {}", operations);
        return operations;
    }

public List<User> getLimitOperation(String username) throws JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<?> requestEntity = new HttpEntity<>(headers);

    ObjectMapper objectMapper = new ObjectMapper();

    // Выполните запрос и получите ResponseEntity<String>
    ResponseEntity<String> responseEntity = restTemplate.exchange(
            "http://localhost:8080/all_limit_operations/retrieve?username=" + username,
            HttpMethod.GET,
            requestEntity,
            String.class
    );

    // Проверяем статус код ответа


        // Получите строку JSON из ResponseEntity
        String responseBody = responseEntity.getBody();

        // Преобразуйте JSON в список объектов типа Operation
        List<User> operations = objectMapper.readValue(responseBody, new TypeReference<List<User>>() {
        });

        // Теперь у вас есть список операций для использования
        log.info("operations is: {}", operations);
        return operations;
}

public List<Operation> getAllLimitExceededOperation(String username) throws JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<?> requestEntity = new HttpEntity<>(headers);

    ObjectMapper objectMapper = new ObjectMapper();

    // Выполните запрос и получите ResponseEntity<String>
    String url = "http://localhost:8080/all_limit_operations/retrieve?username="+username;
    ResponseEntity<String> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            requestEntity,
            String.class
    );
    // Проверяем статус код ответа

        // Получите строку JSON из ResponseEntity
        String responseBody = responseEntity.getBody();

        // Преобразуйте JSON в список объектов типа Operation
        List<Operation> operations = objectMapper.readValue(responseBody, new TypeReference<List<Operation>>() {
        });

        // Теперь у вас есть список операций для использования
        log.info("operations is: {}", operations);
        return operations;
}

public boolean setNewLimit(User user) {
    String url = "http://localhost:8080/make_new_limit/retrieve";

    // Создание объекта RestTemplate
    RestTemplate restTemplate = new RestTemplate();

    // Установка заголовков запроса
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Создание объекта HttpEntity с переданным пользователем и заголовками
    HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

    // Отправка POST-запроса и получение ответа
    ResponseEntity<String> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            String.class
    );

    // Обработка ответа
    HttpStatusCode statusCode = responseEntity.getStatusCode();
    if (statusCode == HttpStatus.OK) {
        System.out.println("New limit set successfully.");
    } else {
        System.out.println("Failed to set new limit. Status code: " + statusCode);
    }
    return true;
}
}
