package com.coderdot.repository;

import com.coderdot.entities.User;
import com.coderdot.models.UserSummary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT new com.coderdot.models.UserSummary(u.id, u.nombres, u.dni, u.celular, u.username, u.email) FROM User u")
    List<UserSummary> findAllUserSummaries();

    @Query("SELECT new com.coderdot.models.UserSummary(u.id, u.nombres, u.dni, u.celular, u.username, u.email) FROM User u WHERE u.id = :userId")
    Optional<UserSummary> findUserSummaryById(@Param("userId") Long userId);
}
