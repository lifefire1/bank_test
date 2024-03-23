package bank.server.service;

import bank.server.entity.Operation;
import bank.server.entity.User;
import bank.server.repository.OperationRepository;
import bank.server.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Api(tags = "Client Service", description = "Service for client operations")
public class ClientService {
    private final OperationRepository operationRepository;
    private final UserRepository userRepository;

    public ClientService(OperationRepository operationRepository, UserRepository userRepository) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
    }

    @ApiOperation("Get all operations for a user by user ID")
    public List<Operation> getAllOperations(Long userId){
        Optional<User> tempUser = userRepository.findById(userId);
        ArrayList<Operation> list;
        if(tempUser.isPresent()){
            list = (ArrayList<Operation>) operationRepository.findAllByUser(tempUser.get());
        }
        else {
            log.info("User with id not found");
            return null;
        }
        return list;
    }

    @ApiOperation("Get all limit operations by username")
    public List<User> getAllLimit(String username){
        return userRepository.findAllByUsernameOrderByDateDesc(username);
    }

    @ApiOperation("Get all limit exceeded operations by username")
    public List<Operation> getAlLimitExceededOperations(String username) {
        Optional<User> tempUser = userRepository.findFirstByUsername(username);
        log.info("Received data: {}", tempUser);
        List<Operation> res = operationRepository.findAllByLimitExceededAndUser(true, tempUser.get());
        log.info("Found data: {}", res);
        return res;
    }

    @ApiOperation("Set new limit for a user")
    public boolean setNewLimit(@RequestBody User user) {
        Optional<User> tmp = userRepository.findFirstByUsernameOrderByDateDesc(user.getUsername());
        user.setDate(new Date());
        if(user.getServiceLimits() == null){
            user.setServiceLimits(tmp.get().getServiceLimits());
        }
        if(user.getProductLimits() == null){
            user.setProductLimits(tmp.get().getProductLimits());
        }

        userRepository.save(user);
        return true;
    }
}
