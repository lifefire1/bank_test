package com.example.demo.repository;

import com.example.demo.entity.Operation;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByUser(User user);
    List<Operation> findAllByLimitExceededAndUser(boolean limitExceeded, User user);
}
