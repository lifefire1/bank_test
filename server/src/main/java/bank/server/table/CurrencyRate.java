package bank.server.table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;

import bank.server.entity.Currency;

@Data
@Table("currency_rates")
@Getter
@Setter
@ApiModel(description = "Currency Rate Entity")
public class CurrencyRate {

    @Id
    @PrimaryKey
    @ApiModelProperty(notes = "The currency code", example = "USD")
    private Currency currency;

    @ApiModelProperty(notes = "The currency exchange rate")
    private BigDecimal rate;

    @ApiModelProperty(notes = "The timestamp when the exchange rate was updated")
    private long timestamp;

    public CurrencyRate(Currency currency, BigDecimal one, long l) {
        this.currency = currency;
        this.rate = one;
        this.timestamp = l;
    }

    public CurrencyRate() {
    }
}
