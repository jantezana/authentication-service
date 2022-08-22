package com.nisum.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nisum.authenticationservice.dto.UserDto;
import com.nisum.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    private List<UserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    //creating a get mapping that retrieves the detail of a specific book
    @GetMapping("/users/{userId}")
    private UserDto getUser(@PathVariable("userId") Long userId) {
        return this.userService.getUserById(userId);
    }

    @PostMapping("/users")
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
    private UserDto updateUser(@PathVariable("userId") Long userId, @RequestBody JsonPatch patch) throws Exception {
        UserDto updatedUser = this.userService.update(userId, patch);
        return updatedUser;
    }

    @DeleteMapping(path = "/users/{userId}")
    private void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.delete(userId);
    }


}
