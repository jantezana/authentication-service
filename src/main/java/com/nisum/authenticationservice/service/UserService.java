package com.nisum.authenticationservice.service;

import com.nisum.authenticationservice.dto.UserDto;

public interface UserService {

    UserDto signup(UserDto userDto);
    UserDto findUserByEmail(String email);
    UserDto updateProfile(UserDto userDto);
    UserDto changePassword(UserDto userDto, String newPassword);
}
