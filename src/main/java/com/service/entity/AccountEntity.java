package com.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class AccountEntity extends AbstractEntity{

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @Column(nullable = false)
    private Boolean isEnabled;

}
