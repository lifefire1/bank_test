package bank.client.representation;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class Operation {

    private Long id;

    private BigDecimal value;

    private Currency currency;

    private Date date;

    private OperationType type;

    private boolean limitExceeded;

    private User user;

    private BigDecimal currentLimit;

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", value=" + value +
                ", currency=" + currency +
                ", date=" + date +
                ", type=" + type +
                ", limitExceeded=" + limitExceeded +
                ", user=" + (user != null ? user.getId() : null) +
                '}';
    }
}
