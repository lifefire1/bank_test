package bank.server.repository;

import bank.server.entity.UserSpending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSpendingRepository extends JpaRepository<UserSpending,Long> {
    @Query("SELECT u FROM UserSpending u WHERE u.user.id = :userId")
    UserSpending findByUserId(@Param("userId") Long userId);
}
