package com.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CommentEntity extends AbstractEntity {

    @ManyToOne
    private AccountEntity createBy;

    private String value;

    private LocalDateTime dateCreate;

    @ManyToOne
    @JoinColumn(name="repair_id")
    private RepairEntity repairEntity;

}
