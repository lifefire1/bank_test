package com.example.demo.repository;

import com.example.demo.entity.UserSpending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpendingRepository extends JpaRepository<UserSpending,Long> {
    @Query("SELECT u FROM UserSpending u WHERE u.user.id = :userId")
    UserSpending findByUserId(@Param("userId") Long userId);
}
