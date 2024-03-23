package com.example.demo.controller;

import com.example.demo.entity.Operation;
import com.example.demo.entity.User;
import com.example.demo.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Tag(name = "Client methods")
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
