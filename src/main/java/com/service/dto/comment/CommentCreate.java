package com.service.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentCreate {

    @NotNull
    private Long repairId;

    @NotBlank
    private String value;

}
