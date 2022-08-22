package com.nisum.authenticationservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String number;
    private String cityCode;
    private String countryCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
