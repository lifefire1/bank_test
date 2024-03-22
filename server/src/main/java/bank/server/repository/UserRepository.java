package bank.server.repository;

import bank.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    Optional<User> findAllByUsernameOrderByDateDesc(String username);

    Optional<User> findFirstByUsernameOrderByDateDesc(String username);

    List<User> findAllByUsernameOrderByDateDesc(String username);

    Optional<User> findFirstByUsername(String username);

}
