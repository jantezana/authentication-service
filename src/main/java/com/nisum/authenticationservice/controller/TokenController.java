package com.nisum.authenticationservice.controller;

import com.nisum.authenticationservice.dto.LoggedUser;
import com.nisum.authenticationservice.exception.LoggedUserException;
import com.nisum.authenticationservice.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * TokenController class.
 *
 * @author jantezana
 */
@RestController
@Api(tags = "Tokens")
public class TokenController {

    private final UserService userService;

    @Autowired
    public TokenController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets the generated token.
     *
     * @param email    The email
     * @param password The password
     * @return The generated token
     */
    @PostMapping("/tokens")
    @ResponseStatus(code = HttpStatus.OK)
    public LoggedUser getToken(@RequestParam("email") final String email, @RequestParam("password") final String password) {
        String token = this.userService.login(email, password);
        if (StringUtils.isEmpty(token)) {
            throw new LoggedUserException("no token found");
        }

        LoggedUser loggedUser = new LoggedUser();
        loggedUser.setToken(token);
        return loggedUser;
    }
}