package com.service.dto.account;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ShortInfoRepair {
    private Long id;
    private String shortDescription;
    private String executor;
    private RepairStatus status;
    private LocalDateTime dateCreated;
    private LocalDate expectedCompletionDate;
}
