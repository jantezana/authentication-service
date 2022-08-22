package com.nisum.authenticationservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Phone {

    @Id
    private String number;
    private String cityCode;
    private String countryCode;

    @ManyToOne
    @JsonBackReference("User_Phone")
    private User user;
}
