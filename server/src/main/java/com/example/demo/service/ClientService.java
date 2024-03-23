package com.example.demo.service;

import com.example.demo.entity.Operation;
import com.example.demo.entity.User;
import com.example.demo.repository.OperationRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {
    private final OperationRepository operationRepository;
    private final UserRepository userRepository;

    public ClientService(OperationRepository operationRepository, UserRepository userRepository) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
    }

    public List<Operation> getAllOperations(Long userId){
        Optional<User> tempUser = userRepository.findById(userId);
        ArrayList<Operation> list;
        if(tempUser.isPresent()){
            list = (ArrayList<Operation>) operationRepository.findAllByUser(tempUser.get());
        }
        else {
            log.info("user with id not found");
            return null;
        }
        return list;
    }

    public List<User> getAllLimit(String username){
        return userRepository.findAllByUsernameOrderByDateDesc(username);
    }

    public List<Operation> getAlLimitExceededOperations(String username) {
        Optional<User> tempUser = userRepository.findFirstByUsername(username);
        log.info("receive data is : {}", tempUser);
        List<Operation> res = operationRepository.findAllByLimitExceededAndUser(true, tempUser.get());
        log.info("find data is : {}", res);;
        return res;
    }

    public boolean setNewLimit(User user) {
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
