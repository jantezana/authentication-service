package com.nisum.authenticationservice.config;

import com.nisum.authenticationservice.dto.PhoneDto;
import com.nisum.authenticationservice.dto.UserDto;
import com.nisum.authenticationservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataSeed {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDataSeed.class);

    @Bean
    public CommandLineRunner initDatabase(final UserService userService) {
        return args -> {
            UserDto userDto = new UserDto();
            userDto.setName("Juan Rodriguez");
            userDto.setEmail("juan@rodriguez.org");
            userDto.setPassword("hunter2");

            PhoneDto phoneDto = new PhoneDto();
            phoneDto.setNumber("1234567");
            phoneDto.setCityCode("1");
            phoneDto.setCountryCode("57");

            userDto.getPhones().add(phoneDto);

            UserDto savedUser = userService.save(userDto);
            LOGGER.info("Preloading {}", savedUser);
        };
    }
}
