package bank.server.table;

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
public class CurrencyRate {

    @Id
    @PrimaryKey
    private Currency currency;
    private BigDecimal rate;
    private long timestamp;

    public CurrencyRate(Currency currency, BigDecimal one, long l) {

    }

    public CurrencyRate() {
    }
}
