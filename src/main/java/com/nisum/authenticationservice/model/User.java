package com.nisum.authenticationservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
@Getter
@Setter
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference("User_Phone")
    private Set<Phone> phones;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Calendar createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date", nullable = false)
    @LastModifiedDate
    private Calendar modifiedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastLogin;

    private boolean active;
    private String token;

    public User() {
        this.phones = new HashSet<>();
        this.active = Boolean.TRUE;
    }
}
