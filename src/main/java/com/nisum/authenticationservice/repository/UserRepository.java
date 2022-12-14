package com.nisum.authenticationservice.repository;

import com.nisum.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * UserRepository class.
 *
 * @author jantezana
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u where u.email = ?1 and u.password = ?2")
    Optional<User> login(final String email, final String password);

    /**
     * Finds by token.
     *
     * @param token The token
     * @return The user optional
     */
    Optional<User> findByToken(final String token);
}
