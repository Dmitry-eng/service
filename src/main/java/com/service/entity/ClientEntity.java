package com.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@FieldNameConstants
public class ClientEntity extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    private String phoneNumber;

    private String email;

    @ManyToOne(optional = false)
    private AccountEntity createBy;

    private LocalDateTime createDate;
}

