package bank.server.controller;

import bank.server.service.UpdateCurrencyRateService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/process-closed-prices")
@Slf4j
@Api(tags = "Currency Controller", description = "A controller for receiving a json object with currency rates")
public class CurrencyController {

    private final UpdateCurrencyRateService updateCurrencyRateService;

    @Autowired
    public CurrencyController(UpdateCurrencyRateService updateCurrencyRateService) {
        this.updateCurrencyRateService = updateCurrencyRateService;
    }


    @PostMapping
    public ResponseEntity<String> processClosedPrices(@RequestBody Map<String, String> closedPrices) {
        log.info("Полученные закрытые цены: " + closedPrices);
        updateCurrencyRateService.update(closedPrices);
        return new ResponseEntity<>("Данные успешно получены и обработаны", HttpStatus.OK);
    }

}
