package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private BigDecimal productLimits;

    private BigDecimal serviceLimits;

    private Date date;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Operation> operationList;


}
