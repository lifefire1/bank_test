package bank.server.repository;

import bank.server.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Api(tags = "User Repository", description = "Repository for accessing user data")
public interface UserRepository extends JpaRepository<User, Long> {

    @ApiOperation("Find first user by username ordered by date descending")
    Optional<User> findFirstByUsernameOrderByDateDesc(String username);

    @ApiOperation("Find all users by username ordered by date descending")
    List<User> findAllByUsernameOrderByDateDesc(String username);

    @ApiOperation("Find first user by username")
    Optional<User> findFirstByUsername(String username);

}
