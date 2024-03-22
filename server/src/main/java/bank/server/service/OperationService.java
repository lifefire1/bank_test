package bank.server.service;

import bank.server.entity.*;
import bank.server.repository.CurrencyRateRepository;
import bank.server.repository.OperationRepository;
import bank.server.repository.UserRepository;
import bank.server.repository.UserSpendingRepository;
import bank.server.table.CurrencyRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class OperationService {

    private final OperationRepository operationRepository;
    private final UserSpendingRepository userSpendingRepository;

    private final UserRepository userRepository;

    private final CurrencyRateRepository repository;

    public OperationService(OperationRepository operationRepository, UserSpendingRepository userSpendingRepository, UserRepository userRepository, CurrencyRateRepository repository) {
        this.operationRepository = operationRepository;
        this.userSpendingRepository = userSpendingRepository;
        this.userRepository = userRepository;
        this.repository = repository;
    }


    public boolean checkOperation(Operation operation) {
        if (operation == null || operation.getUser() == null || operation.getCurrency() == null || operation.getValue().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        UserSpending currentSum = userSpendingRepository.findByUserId(operation.getUser().getId());
        if (currentSum == null) {
            log.info("current sum is null");
            return false;
        }

        log.info("currentSum: {}", currentSum);
        Optional<User> tempUser1 = userRepository.findById(operation.getUser().getId());
        Optional<User> resUser = userRepository.findFirstByUsernameOrderByDateDesc(tempUser1.get().getUsername());

        // Установка значений по умолчанию для нулевых лимитов
        if (resUser.isPresent()) {
            if (resUser.get().getProductLimits() == null || resUser.get().getProductLimits().compareTo(BigDecimal.ZERO) <= 0) {
                resUser.get().setProductLimits(BigDecimal.valueOf(1000));
            }
            if (resUser.get().getServiceLimits() == null || resUser.get().getServiceLimits().compareTo(BigDecimal.ZERO) <= 0) {
                resUser.get().setServiceLimits(BigDecimal.valueOf(1000));
            }
        }

        log.info("limit from user: {} ", resUser);

        if (operation.getType() == OperationType.SERVICE && isExceedingServiceLimit(currentSum, resUser, operation.getValue(), operation.getCurrency())) {
            log.info("limit service");
            operation.setLimitExceeded(true);
            return false;
        } else if (operation.getType() == OperationType.PRODUCT && isExceedingProductLimit(currentSum, resUser, operation.getValue(),operation.getCurrency())) {
            log.info("limit product");
            operation.setLimitExceeded(true);
            return false;
        }

        return true;
    }


    private boolean isExceedingServiceLimit(UserSpending currentSum, Optional<User> tempUser, BigDecimal value, Currency currency) {
        Optional<CurrencyRate> rate = repository.findByCurrency(currency);
        if (rate.isPresent()) {
            BigDecimal rateValue = rate.get().getRate();
            BigDecimal convertedValue = value.divide(rateValue, 10, RoundingMode.HALF_UP); // Округляем до двух знаков после запятой

            // Логгирование суммы операции в текущей валюте
            log.info("Operation value in current currency ({}): {}", currency, value);

            // Логгирование суммы операции в переводе
            log.info("Operation value in converted currency (USD): {}", convertedValue);

            return currentSum.getCurrentSumService().add(convertedValue).compareTo(tempUser.orElseThrow().getServiceLimits()) > 0;
        } else {
            throw new RuntimeException("Currency rate not found for currency: " + currency);
        }
    }


    private boolean isExceedingProductLimit(UserSpending currentSum, Optional<User> tempUser, BigDecimal value, Currency currency) {
        // Логгирование суммы операции в текущей валюте
        log.info("Operation value in current currency ({}): {}", currency, value);

        // Получаем курс валюты из репозитория
        Optional<CurrencyRate> rate = repository.findByCurrency(currency);
        if (rate.isPresent()) {
            BigDecimal rateValue = rate.get().getRate();
            BigDecimal convertedValue = value.divide(rateValue, 10, RoundingMode.HALF_UP); // Округляем до двух знаков после запятой

            // Логгирование суммы операции в переводе
            log.info("Operation value in converted currency (USD): {}", convertedValue);

            return currentSum.getCurrentSumProduct().add(convertedValue).compareTo(tempUser.orElseThrow().getProductLimits()) > 0;
        } else {
            throw new RuntimeException("Currency rate not found for currency: " + currency);
        }
    }

    public boolean saveOperation(Operation operation){
        if(operation == null || !checkOperation(operation)){
            if(operation != null && operation.isLimitExceeded()){
                operation.setDate(new Date());
                operationRepository.save(operation);
            }
            return false;
        }

        log.info("received operation {}", operation);

        Optional<CurrencyRate> rate = repository.findByCurrency(operation.getCurrency());
        BigDecimal rateValue = rate.get().getRate();
        UserSpending currentSum = userSpendingRepository.findByUserId(operation.getUser().getId());
        log.info("current sum : {}", currentSum);
        if(operation.getType() == OperationType.SERVICE){
            currentSum.setCurrentSumService(currentSum.getCurrentSumService().add(operation.getValue().divide(rateValue, 10, RoundingMode.HALF_UP)));
        }
        else{
            currentSum.setCurrentSumProduct(currentSum.getCurrentSumProduct().add(operation.getValue().divide(rateValue, 10, RoundingMode.HALF_UP)));
        }
        userSpendingRepository.save(currentSum);
        operation.setDate(new Date());
        operationRepository.save(operation);

        return true;
    }
}
