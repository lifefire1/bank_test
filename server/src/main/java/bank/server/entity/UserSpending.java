package bank.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@ApiModel(description = "Entity representing user spending statistics")
public class UserSpending {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Unique identifier for the user spending", example = "1")
    private Long id;

    @OneToOne
    @ApiModelProperty(value = "User associated with the spending statistics")
    private User user;

    @ApiModelProperty(value = "Current sum spent on services", example = "500.00")
    private BigDecimal currentSumService;

    @ApiModelProperty(value = "Current sum spent on products", example = "1000.00")
    private BigDecimal currentSumProduct;

    @Override
    public String toString() {
        return "UserSpending{" + "id=" + id + ", user=" + (user != null ? user.getId() : null) + ", currentSumService=" + currentSumService + ", currentSumProduct=" + currentSumProduct + '}';
    }
}
