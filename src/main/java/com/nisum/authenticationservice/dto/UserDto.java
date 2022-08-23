package com.nisum.authenticationservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<PhoneDto> phones;
    private ZonedDateTime createdDate;
    private ZonedDateTime modifiedDate;
    private ZonedDateTime lastLogin;
    private boolean active;

    public UserDto() {
        this.phones = new HashSet<>();
        this.active = true;
    }
}
