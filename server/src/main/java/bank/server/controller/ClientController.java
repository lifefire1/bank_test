package bank.server.controller;

import bank.server.entity.Operation;
import bank.server.entity.User;
import bank.server.repository.OperationRepository;
import bank.server.repository.UserRepository;
import bank.server.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class ClientController {
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;

    }

    @GetMapping("/all_operations/retrieve")
    public List<Operation> retrieveAllOperations(@RequestParam("userId") Long userId) {
        ArrayList<Operation> operations = (ArrayList<Operation>) clientService.getAllOperations(userId);
        log.info(operations.toString());
        return operations;
    }

    @GetMapping("/all_limit_operations/retrieve")
    public List<User> retrieveAllLimitOperations(@RequestParam("username") String username) {
        ArrayList<User> operations = (ArrayList<User>) clientService.getAllLimit(username);
        log.info(operations.toString());
        return operations;
    }

    @GetMapping("/all_limit_exceeded/retrieve")
    public List<Operation> retrieveAlLimitExceededOperations(@RequestParam("username") String username) {
        ArrayList<Operation> operations = (ArrayList<Operation>) clientService.getAlLimitExceededOperations(username);
        log.info(operations.toString());
        return operations;
    }

    @PostMapping("/make_new_limit/retrieve")
    public ResponseEntity<Void> setNewLimit(@RequestBody User user){
        boolean status = clientService.setNewLimit(user);
        if(status){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}
