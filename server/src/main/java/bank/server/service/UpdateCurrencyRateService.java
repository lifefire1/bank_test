package bank.server.service;

import io.swagger.annotations.Api;
import bank.server.entity.Currency;
import bank.server.repository.CurrencyRateRepository;
import bank.server.table.CurrencyRate;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@Api(tags = "Update Currency Rate Service", description = "Service for updating currency rates")
public class UpdateCurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;

    @Autowired
    public UpdateCurrencyRateService(CurrencyRateRepository currencyRateRepository) {
        this.currencyRateRepository = currencyRateRepository;
    }

    @ApiOperation("Update currency rates")
    public void update(Map<String, String> closedPrices) {
        for (Map.Entry<String, String> entry : closedPrices.entrySet()) {
            String currencyCode = entry.getKey().replace("\"", "");
            String rateString = entry.getValue().replace("\"", "");

            Optional<CurrencyRate> existingRateOptional = currencyRateRepository.findByCurrency(Currency.valueOf(currencyCode));

            // Проверяем, существует ли запись
            if (existingRateOptional.isPresent()) {
                CurrencyRate existingRate = existingRateOptional.get();
                // Если запись существует, обновляем ее
                existingRate.setRate(new BigDecimal(rateString)); // Обновляем курс валюты
                existingRate.setTimestamp(System.currentTimeMillis()); // Обновляем временную метку
                currencyRateRepository.save(existingRate); // Сохраняем обновленную запись
            } else {
                // Если запись не существует, создаем новую
                CurrencyRate newRate = new CurrencyRate();
                newRate.setCurrency(Currency.valueOf(currencyCode)); // Устанавливаем код валюты
                newRate.setRate(new BigDecimal(rateString)); // Устанавливаем курс валюты
                newRate.setTimestamp(System.currentTimeMillis()); // Устанавливаем временную метку
                currencyRateRepository.save(newRate); // Сохраняем новую запись
            }
        }
    }
}
