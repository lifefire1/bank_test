package bank.server.contrllerTest;

import bank.server.controller.OperationController;
import bank.server.entity.Operation;
import bank.server.service.OperationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperationControllerTest {

    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationController operationController;

    @Test
    void testHello() {
        // Создаем тестовую операцию
        Operation operation = new Operation();
        operation.setValue(BigDecimal.TEN);
        operation.setDate(new Date());

        // Ожидаемый результат
        ResponseEntity<Void> expectedResponse = ResponseEntity.ok().build();

        // Мокируем поведение метода saveOperation
        when(operationService.saveOperation(operation)).thenReturn(true);

        // Вызываем метод контроллера
        ResponseEntity<Void> actualResponse = operationController.hello(operation);

        // Проверяем, что метод saveOperation был вызван один раз с переданной операцией
        verify(operationService, times(1)).saveOperation(operation);

        // Проверяем, что возвращаемый ответ соответствует ожидаемому результату
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }
}
