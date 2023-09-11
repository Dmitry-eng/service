package com.service.dto.repair;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ShortInfoRepair {

    private Long id;

    private String shortDescription;

    private String executor;

    private RepairStatus status;

    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")

    private LocalDateTime dateCreated;

    private LocalDate completionDate;

}
