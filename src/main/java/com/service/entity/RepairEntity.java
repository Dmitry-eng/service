package com.service.entity;

import com.service.dto.repair.RepairStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@Entity
@Data
@ToString(exclude = "comments")
public class RepairEntity extends AbstractEntity {

    @Column(nullable = false)
    private String shortDescription;

    private String fullDescription;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    private LocalDateTime completionDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RepairStatus status;

    private String address;

    private String phoneNumber;

    private BigDecimal price;

    @ManyToOne
    private ClientEntity client;

    @ManyToOne
    private AccountEntity executor;

    @ManyToOne(optional = false)
    private AccountEntity createdBy;

    @OneToMany
    @JoinColumn(name = "repair_id")
    private List<CommentEntity> comments;

}
