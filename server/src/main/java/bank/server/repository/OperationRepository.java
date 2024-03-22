package bank.server.repository;

import bank.server.entity.Operation;
import bank.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByUser(User user);
    List<Operation> findAllByLimitExceededAndUser(boolean limitExceeded, User user);
}
