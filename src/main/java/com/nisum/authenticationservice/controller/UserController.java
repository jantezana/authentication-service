package com.nisum.authenticationservice.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.nisum.authenticationservice.dto.UserDto;
import com.nisum.authenticationservice.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController class.
 *
 * @author jantezana
 */
@RestController
@Api( tags = "Users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets the list of users.
     *
     * @return The list of users.
     */
    @GetMapping("/users")
    @ResponseStatus(code = HttpStatus.OK)
    private List<UserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    /**
     * Gets the user by Id.
     *
     * @param userId The user Id
     * @return The found user
     */
    @GetMapping("/users/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    private UserDto getUser(@PathVariable("userId") Long userId) {
        return this.userService.getUserById(userId);
    }

    /**
     * Creates a new user.
     *
     * @param user The new user
     * @return The created user
     */
    @PostMapping("/users")
    @ResponseStatus(code = HttpStatus.CREATED)
    private UserDto saveUser(@RequestBody UserDto user) {
        UserDto savedUser = this.userService.save(user);
        return savedUser;
    }

    /**
     * Updates the user.
     *
     * @param userId the user ID
     * @param patch  the patch body
     * @return the updated user
     * @see <a href="https://www.baeldung.com/spring-rest-json-patch">Using JSON Patch in Spring REST APIs</a>
     */
    @PatchMapping(path = "/users/{userId}", consumes = {"application/json-patch+json"}, produces = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    private UserDto updateUser(@PathVariable("userId") Long userId, @RequestBody JsonPatch patch) throws Exception {
        UserDto updatedUser = this.userService.update(userId, patch);
        return updatedUser;
    }

    /**
     * Deletes a user by Id.
     *
     * @param userId The user Id
     */
    @DeleteMapping(path = "/users/{userId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    private void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.delete(userId);
    }
}
