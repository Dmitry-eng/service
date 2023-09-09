package com.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@Entity
@Data
public class RepairEntity extends AbstractEntity {

    @Column(nullable = false)
    private String shortDescription;
    private String fullDescription;
    @Column(nullable = false)
    private LocalDateTime dateCreated;
    private LocalDate expectedCompletionDate;
    @Column(nullable = false)
    private String status;

    @ManyToOne
    private AccountEntity executor;
}
