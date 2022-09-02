package com.nisum.authenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * AuthenticationServiceApplication class.
 *
 * @author jantezana
 */
@SpringBootApplication
@EnableJpaAuditing
public class AuthenticationServiceApplication {

    /**
     * Main method.
     *
     * @param args The arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }

}
