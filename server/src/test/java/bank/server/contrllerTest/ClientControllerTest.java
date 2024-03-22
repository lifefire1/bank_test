package bank.server.contrllerTest;

import bank.server.entity.Operation;
import bank.server.entity.User;
import bank.server.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;


    @Test
    public void testRetrieveAllOperations() throws Exception {
        List<Operation> operations = new ArrayList<>(); // создаем тестовые данные
        // Задаем ожидаемое поведение при вызове метода clientService.getAllOperations(userId)
        when(clientService.getAllOperations(anyLong())).thenReturn(operations);

        mockMvc.perform(get("/all_operations/retrieve")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray()); // Проверяем, что ответ является массивом JSON
    }

    @Test
    void testMakeNewLimit() throws Exception {
        String requestBody = "{\"username\": \"vasia\", \"productLimits\": 2000}";

        mockMvc.perform(MockMvcRequestBuilders.post("/make_new_limit/retrieve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRetrieveAllLimitOperations() throws Exception {
        List<User> users = new ArrayList<>(); // создаем тестовые данные
        // Задаем ожидаемое поведение при вызове метода clientService.getAllLimit(username)
        when(clientService.getAllLimit(anyString())).thenReturn(users);

        mockMvc.perform(get("/all_limit_operations/retrieve")
                        .param("username", "testUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray()); // Проверяем, что ответ является массивом JSON
    }

    @Test
    public void testRetrieveAllLimitExceededOperations() throws Exception {
        List<Operation> operations = new ArrayList<>(); // создаем тестовые данные
        // Задаем ожидаемое поведение при вызове метода clientService.getAlLimitExceededOperations(username)
        when(clientService.getAlLimitExceededOperations(anyString())).thenReturn(operations);

        mockMvc.perform(get("/all_limit_exceeded/retrieve")
                        .param("username", "testUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray()); // Проверяем, что ответ является массивом JSON
    }
}
