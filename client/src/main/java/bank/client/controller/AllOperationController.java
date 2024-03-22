package bank.client.controller;

import bank.client.representation.Operation;
import bank.client.representation.User;
import bank.client.service.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
//@RequestMapping
@Slf4j
public class AllOperationController {
    private final OperationService operationService;

    public AllOperationController(OperationService operationService) {
        this.operationService = operationService;
    }


    @PostMapping("/all_operations")
    public List<Operation> getAllOperations(@RequestBody User user) throws JsonProcessingException {
        return operationService.getAllOperation(user.getId());
    }

    @PostMapping("/all_limit")
    public List<User> getAllLimitOperations(@RequestBody User user) throws JsonProcessingException {
        return operationService.getLimitOperation(user.getUsername());
    }

    @PostMapping("/all_limit_exceeded")
    public List<Operation> getAllLimitExceeded(@RequestBody User user) throws JsonProcessingException {
        return operationService.getAllLimitExceededOperation(user.getUsername());
    }

    @PostMapping("/make_new_limit")
    public boolean setNewLimit(@RequestBody User user){
        operationService.setNewLimit(user);
        return true;
    }
}
