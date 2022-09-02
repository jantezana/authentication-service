package com.nisum.authenticationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nisum.authenticationservice.dto.UserDto;
import com.nisum.authenticationservice.dto.mapper.UserMapper;
import com.nisum.authenticationservice.exception.UserNotFoundException;
import com.nisum.authenticationservice.model.User;
import com.nisum.authenticationservice.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final Validator validator;

    @Autowired
    public UserService(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = new LinkedList<>();
        List<User> users = this.userRepository.findAll();
        if (Objects.nonNull(users)) {
            users.forEach(user -> Hibernate.initialize(user.getPhones()));
            userDtos = new LinkedList<>(UserMapper.toUserDtos(users));
        }

        return userDtos;
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(final Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            final String message = String.format("The user with id: %d was not found", userId);
            throw new UserNotFoundException(message);
        }
        User user = userOptional.get();
        Hibernate.initialize(user.getPhones());
        UserDto userDto = UserMapper.toUserDto(user);
        return userDto;
    }

    public UserDto save(final UserDto userDto) {
        Set<ConstraintViolation<UserDto>> violations = this.validator.validate(userDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        User user = UserMapper.toUser(userDto);
        User savedUser = this.userRepository.save(user);
        return UserMapper.toUserDto(savedUser);
    }

    public UserDto update(final Long userId, JsonPatch patch) throws Exception {
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

    public void delete(final Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            final String message = String.format("The user with id: %d was not found", userId);
            throw new UserNotFoundException(message);
        }

        User foundUser = userOptional.get();
        foundUser.setActive(false);
        this.userRepository.save(foundUser);
    }

    public String login(final String email, final String password) {
        Optional<User> userOptional = this.userRepository.login(email, password);
        if (userOptional.isPresent()) {
            String token = UUID.randomUUID().toString();
            User user = userOptional.get();
            user.setToken(token);
            user.setActive(Boolean.TRUE);
            this.userRepository.save(user);
            return token;
        }

        return StringUtils.EMPTY;
    }

    public Optional<org.springframework.security.core.userdetails.User> findByToken(final String token) {
        Optional<User> userOptional = this.userRepository.findByToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(user.getEmail(),
                                                                                                                                   user.getPassword(),
                                                                                                                                   true, true, true,
                                                                                                                                   true,
                                                                                                                                   AuthorityUtils.createAuthorityList(
                                                                                                                                       "USER"));
            return Optional.of(userSpring);
        }
        return Optional.empty();
    }
}
