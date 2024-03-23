package bank.server.controller;

import bank.server.entity.Operation;
import bank.server.service.OperationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/transaction")
@Api(tags = "Operation Controller", description = "Operations related to operation")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<Void> hello(@RequestBody Operation operation) {
        log.info("Received operation: {}", operation);
        boolean status = operationService.saveOperation(operation);
        if (status) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
