package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private BigDecimal value;

    private Currency currency;

    private Date date;

    private OperationType type;

    private boolean limitExceeded;

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

