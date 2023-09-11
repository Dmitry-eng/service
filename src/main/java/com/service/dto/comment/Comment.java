package com.service.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.dto.account.AccountInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {

    private AccountInfo account;

    private String value;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateCreate;

}
