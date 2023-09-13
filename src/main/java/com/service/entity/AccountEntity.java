package com.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldNameConstants
@Entity
public class AccountEntity extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @Column(nullable = false)
    private boolean activated;

}
