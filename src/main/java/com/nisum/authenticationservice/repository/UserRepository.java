package com.nisum.authenticationservice.repository;

import com.nisum.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u where u.email = ?1 and u.password = ?2")
    Optional<User> login(String username, String password);

    @Query(value = "SELECT u FROM User u where u.token = ?1")
    Optional<User> findByToken(String token);
}
