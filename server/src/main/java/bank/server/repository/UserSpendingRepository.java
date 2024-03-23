package bank.server.repository;

import bank.server.entity.UserSpending;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Api(tags = "User Spending Repository", description = "Repository for accessing user spending data")
public interface UserSpendingRepository extends JpaRepository<UserSpending, Long> {

    @ApiOperation("Find user spending by user ID")
    @Query("SELECT u FROM UserSpending u WHERE u.user.id = :userId")
    UserSpending findByUserId(@Param("userId") Long userId);
}
