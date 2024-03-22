package bank.client.configTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClientConfigTest {

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testRestTemplateBean() {
        assertNotNull(restTemplate);
    }
}
