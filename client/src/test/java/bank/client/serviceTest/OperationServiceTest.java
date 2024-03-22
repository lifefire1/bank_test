package bank.client.serviceTest;
import bank.client.representation.Operation;
import bank.client.representation.User;
import bank.client.service.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OperationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OperationService operationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOperation() throws JsonProcessingException {
        // Mocking the ResponseEntity
        String responseBody = "[{\"id\": 1 }]";
        ResponseEntity<String> mockedResponseEntity = mock(ResponseEntity.class);
        when(mockedResponseEntity.getBody()).thenReturn(responseBody);

        // Mocking the RestTemplate
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.exchange(
                eq("http://localhost:8080/all_operations/retrieve?userId=1"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(mockedResponseEntity);

        // Creating an instance of OperationService with the mocked RestTemplate
        OperationService operationService = new OperationService(restTemplate);

        // Test
        List<Operation> operations = operationService.getAllOperation(1L);

        // Assertions
        assertNotNull(operations);
        assertFalse(operations.isEmpty());
        assertEquals(1, operations.size());
        assertEquals(1L, operations.get(0).getId());
    }



    @Test
    void testGetLimitOperation() throws JsonProcessingException {
        // Mocking the ResponseEntity
        String responseBody = "[{\"username\":\"testUser\"}]";
        ResponseEntity<String> mockedResponseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        // Mocking the RestTemplate
       // RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.exchange(
                eq("http://localhost:8080/all_limit_operations/retrieve?username=testUser"),
                eq(HttpMethod.GET),
                any(),
                eq(String.class)
        )).thenReturn(mockedResponseEntity);

        // Creating an instance of OperationService with the mocked RestTemplate
        OperationService operationService = new OperationService(restTemplate);

        // Test
        List<User> users = operationService.getLimitOperation("testUser");

        // Assertions
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals("testUser", users.get(0).getUsername());
    }

    @Test
    void testGetAllLimitExceededOperation() throws JsonProcessingException {
        // Mocking the ResponseEntity
        String responseBody = "[{\"value\": 100}]";
        ResponseEntity<String> mockedResponseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        // Mocking the RestTemplate
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.exchange(
                eq("http://localhost:8080/all_limit_operations/retrieve?username=testUser"),
                any(),
                any(),
                eq(String.class)
        )).thenReturn(mockedResponseEntity);

        // Creating an instance of OperationService with the mocked RestTemplate
        OperationService operationService = new OperationService(restTemplate);

        // Test
        List<Operation> operations = operationService.getAllLimitExceededOperation("testUser");

        // Assertions
        assertNotNull(operations);
        assertFalse(operations.isEmpty());
        assertEquals(1, operations.size());
        assertFalse(operations.get(0).isLimitExceeded());
    }

}
