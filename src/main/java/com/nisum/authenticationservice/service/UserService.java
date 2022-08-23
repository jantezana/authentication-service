package com.nisum.authenticationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nisum.authenticationservice.dto.UserDto;
import com.nisum.authenticationservice.dto.mapper.UserMapper;
import com.nisum.authenticationservice.model.User;
import com.nisum.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos =  new LinkedList<>();
        List<User> users = this.userRepository.findAll();
        if (Objects.nonNull(users)) {
            userDtos = new LinkedList<>(UserMapper.toUserDtos(users));
        }

        return userDtos;
    }


    public UserDto getUserById(final Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            final String message = String.format("The user with id: %d was not found", userId);
            throw new EntityNotFoundException(message);
        }
        UserDto userDto = UserMapper.toUserDto(userOptional.get());
        return userDto;
    }

    public UserDto save(final UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        User savedUser = this.userRepository.save(user);
        return UserMapper.toUserDto(savedUser);
    }

    public UserDto update(Long userId, JsonPatch patch) throws Exception {
        UserDto userDto = this.getUserById(userId);
        UserDto customerPatched = this.applyPatchToUser(patch, userDto);
        User userPatched = UserMapper.toUser(customerPatched);
        User updatedUser = this.userRepository.save(userPatched);
        UserDto updatedUserDto = UserMapper.toUserDto(updatedUser);

        return updatedUserDto;
    }

    private UserDto applyPatchToUser(JsonPatch patch, UserDto targetUser) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, UserDto.class);
    }

    public void delete(Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            final String message = String.format("The user with id: %d was not found", userId);
            throw new EntityNotFoundException(message);
        }

        this.userRepository.delete(userOptional.get());
    }
}
