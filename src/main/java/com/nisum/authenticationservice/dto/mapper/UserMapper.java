package com.nisum.authenticationservice.dto.mapper;

import com.nisum.authenticationservice.dto.UserDto;
import com.nisum.authenticationservice.model.Phone;
import com.nisum.authenticationservice.model.User;

import java.util.List;

public class UserMapper {

    public static UserDto toUserDto(final User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        List<Phone> phones = user.getPhones();
        return userDto;
    }
}
