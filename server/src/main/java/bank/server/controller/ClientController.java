package bank.server.controller;

import bank.server.entity.Operation;
import bank.server.entity.User;
import bank.server.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер, обрабатывающий запросы клиентов.
 */

@RestController
@Slf4j
@Api(tags = "Client Controller", description = "Operations related to clients")
public class ClientController {
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;

    }

    /**
     * Получить все операции для пользователя по его идентификатору.
     * @param userId Идентификатор пользователя.
     * @return Список операций пользователя.
     */

    @GetMapping("/all_operations/retrieve")
    @ApiOperation(value = "Retrieve all operations for a user by user ID")
    public List<Operation> retrieveAllOperations(@RequestParam("userId") Long userId) {
        ArrayList<Operation> operations = (ArrayList<Operation>) clientService.getAllOperations(userId);
        log.info(operations.toString());
        return operations;
    }

    /**
     * Получить все операции с лимитом для пользователя по его имени пользователя.
     * @param username Имя пользователя.
     * @return Список операций с лимитом.
     */

    @GetMapping("/all_limit_operations/retrieve")
    @ApiOperation(value = "Get all limit ")
    public List<User> retrieveAllLimitOperations(@RequestParam("username") String username) {
        ArrayList<User> operations = (ArrayList<User>) clientService.getAllLimit(username);
        log.info(operations.toString());
        return operations;
    }

    /**
     * Получить все операции, превышающие лимит, для пользователя по его имени пользователя.
     * @param username Имя пользователя.
     * @return Список операций, превышающих лимит.
     */

    @GetMapping("/all_limit_exceeded/retrieve")
    @ApiOperation(value = "Get all limit exceeded operations")
    public List<Operation> retrieveAlLimitExceededOperations(@RequestParam("username") String username) {
        ArrayList<Operation> operations = (ArrayList<Operation>) clientService.getAlLimitExceededOperations(username);
        log.info(operations.toString());
        return operations;
    }

    /**
     * Установить новый лимит для пользователя.
     * @param user Пользователь, для которого устанавливается новый лимит.
     * @return ResponseEntity с HTTP статусом в зависимости от результата установки лимита.
     */

    @PostMapping("/make_new_limit/retrieve")
    @ApiOperation(value = "Set new limit for a user")
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
