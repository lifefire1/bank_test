package bank.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@ApiModel(description = "Entity representing a banking operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(value = "The unique identifier of the operation", example = "1")
    private Long id;

    @ApiModelProperty(value = "The value of the operation", example = "100.50")
    private BigDecimal value;

    @ApiModelProperty(value = "The currency of the operation", example = "RUB")
    private Currency currency;

    @ApiModelProperty(value = "The date of the operation", example = "2024-03-25T10:15:30Z")
    private Date date;

    @ApiModelProperty(value = "The type of the operation", example = "DEPOSIT")
    private OperationType type;

    @ApiModelProperty(value = "Flag indicating whether the operation exceeded the limit", example = "false")
    private boolean limitExceeded;

    @ApiModelProperty(value = "The current limit for the user in USD", example = "500.00")
    private BigDecimal currentLimit;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

