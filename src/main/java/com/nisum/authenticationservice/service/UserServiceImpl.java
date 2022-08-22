package com.nisum.authenticationservice.service;

import com.nisum.authenticationservice.dto.UserDto;
import com.nisum.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto signup(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        return null;
    }
}
