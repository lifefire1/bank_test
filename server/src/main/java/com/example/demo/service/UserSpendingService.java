package com.example.demo.service;

import com.example.demo.repository.UserSpendingRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSpendingService {
    private final UserSpendingRepository userSpendingRepository;

    public UserSpendingService(UserSpendingRepository userSpendingRepository) {
        this.userSpendingRepository = userSpendingRepository;
    }
}
