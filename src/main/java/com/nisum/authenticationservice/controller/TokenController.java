package com.nisum.authenticationservice.controller;

import com.nisum.authenticationservice.dto.LoggedUser;
import com.nisum.authenticationservice.exception.LoggedUserException;
import com.nisum.authenticationservice.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Tokens")
public class TokenController {

    @Autowired
    private UserService userService;

    @PostMapping("/tokens")
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