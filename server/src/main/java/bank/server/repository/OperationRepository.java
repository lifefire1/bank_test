package bank.server.repository;

import bank.server.entity.Operation;
import bank.server.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Api(tags = "Operation Repository", description = "Repository for accessing operation data")
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @ApiOperation("Find all operations by user")
    List<Operation> findAllByUser(User user);

    @ApiOperation("Find all operations by limit exceeded and user")
    List<Operation> findAllByLimitExceededAndUser(boolean limitExceeded, User user);
}
