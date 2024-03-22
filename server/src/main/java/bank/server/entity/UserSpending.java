package bank.server.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class UserSpending {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    private BigDecimal currentSumService;

    private BigDecimal currentSumProduct;

    @Override
    public String toString() {
        return "UserSpending{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", currentSumService=" + currentSumService +
                ", currentSumProduct=" + currentSumProduct +
                '}';
    }
}
