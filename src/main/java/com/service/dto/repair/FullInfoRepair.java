package com.service.dto.repair;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.dto.account.AccountInfo;
import com.service.dto.client.Client;
import com.service.dto.comment.Comment;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class FullInfoRepair {

    private Long id;

    private String shortDescription;

    private String fullDescription;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime completionDate;

    private RepairStatus status;

    private String address;

    private AccountInfo executor;

    private String contactPhoneNumber;

    private BigDecimal price;

    private Client client;

    private List<Comment> comments;

}
