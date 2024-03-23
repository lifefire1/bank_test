package bank.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@ApiModel(description = "User entity representing a bank user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Unique identifier for the user", example = "1")
    private Long id;

    @ApiModelProperty(value = "Username of the user", example = "john_doe")
    private String username;

    @ApiModelProperty(value = "Product limits for the user", example = "1000.00")
    @JsonProperty("productLimits")
    private BigDecimal productLimits;

    @ApiModelProperty(value = "Service limits for the user", example = "500.00")
    @JsonProperty("serviceLimits")
    private BigDecimal serviceLimits;

    @ApiModelProperty(value = "Date of user registration", example = "2024-03-28")
    private Date date;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Operation> operationList;
}