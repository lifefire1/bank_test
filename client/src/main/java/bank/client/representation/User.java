package bank.client.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;



@Data
public class User {

    private Long id;

    private String username;

    private Long productLimits;

    private Long serviceLimits;

    private Date date;

    private List<Operation> operationList;


}

