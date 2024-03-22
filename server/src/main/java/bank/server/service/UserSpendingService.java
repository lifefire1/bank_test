package bank.server.service;

import bank.server.entity.UserSpending;
import bank.server.repository.UserSpendingRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSpendingService {
    private final UserSpendingRepository userSpendingRepository;

    public UserSpendingService(UserSpendingRepository userSpendingRepository) {
        this.userSpendingRepository = userSpendingRepository;
    }
}
