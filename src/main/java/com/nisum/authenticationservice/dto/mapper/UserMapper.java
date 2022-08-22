package com.nisum.authenticationservice.dto.mapper;

import com.nisum.authenticationservice.dto.PhoneDto;
import com.nisum.authenticationservice.dto.UserDto;
import com.nisum.authenticationservice.model.Phone;
import com.nisum.authenticationservice.model.User;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UserMapper {

    public static Collection<UserDto> toUserDtos(final Collection<User> users) {
        Objects.requireNonNull(users, "The list of users is null");

        Collection<UserDto> userDtos = new LinkedList<>();
        for (User user : users) {
            UserDto userDto = toUserDto(user);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    public static UserDto toUserDto(final User user) {
        Objects.requireNonNull(user, "The user is null");

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        List<Phone> phones = user.getPhones();
        if (Objects.nonNull(phones)) {
            List<PhoneDto> phoneDtos = new LinkedList<>(PhoneMapper.toPhoneDtos(phones));
            userDto.setPhones(phoneDtos);
        }
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        Objects.requireNonNull(userDto, "The userDto is null");

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        List<PhoneDto> phoneDtos = userDto.getPhones();
        if (Objects.nonNull(phoneDtos)) {
            List<Phone> phones = new LinkedList<>(PhoneMapper.toPhones(phoneDtos));
            user.setPhones(phones);
        }
        return user;
    }
}
