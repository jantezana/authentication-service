package com.nisum.authenticationservice.dto.mapper;

import com.nisum.authenticationservice.dto.PhoneDto;
import com.nisum.authenticationservice.dto.UserDto;
import com.nisum.authenticationservice.model.Phone;
import com.nisum.authenticationservice.model.User;
import com.nisum.authenticationservice.util.DateUtil;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

/**
 * UserMapper class.
 *
 * @author jantezana
 */
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
        Set<Phone> phones = user.getPhones();
        if (Objects.nonNull(phones)) {
            Set<PhoneDto> phoneDtos = new HashSet<>(PhoneMapper.toPhoneDtos(phones));
            userDto.setPhones(phoneDtos);
        }

        Calendar createdDate = user.getCreatedDate();
        if (Objects.nonNull(createdDate)) {
            userDto.setCreatedDate(DateUtil.toZoneDateTime(createdDate));
        }

        Calendar modifiedDate = user.getModifiedDate();
        if (Objects.nonNull(modifiedDate)) {
            userDto.setModifiedDate(DateUtil.toZoneDateTime(modifiedDate));
        }

        Calendar lastLogin = user.getLastLogin();
        if (Objects.nonNull(lastLogin)) {
            userDto.setLastLogin(DateUtil.toZoneDateTime(lastLogin));
        }

        userDto.setActive(user.isActive());
        userDto.setToken(user.getToken());

        return userDto;
    }

    public static User toUser(UserDto userDto) {
        Objects.requireNonNull(userDto, "The userDto is null");

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        Set<PhoneDto> phoneDtos = userDto.getPhones();
        if (Objects.nonNull(phoneDtos)) {
            Set<Phone> phones = new HashSet<>(PhoneMapper.toPhones(phoneDtos));
            user.setPhones(phones);
        }

        ZonedDateTime createdDate = userDto.getCreatedDate();
        if (Objects.nonNull(createdDate)) {
            user.setCreatedDate(DateUtil.toCalendar(createdDate));
        }

        ZonedDateTime modifiedDate = userDto.getModifiedDate();
        if (Objects.nonNull(modifiedDate)) {
            user.setModifiedDate(DateUtil.toCalendar(modifiedDate));
        }

        ZonedDateTime lastLogin = userDto.getLastLogin();
        if (Objects.nonNull(lastLogin)) {
            user.setLastLogin(DateUtil.toCalendar(lastLogin));
        }

        user.setActive(userDto.isActive());
        return user;
    }
}
