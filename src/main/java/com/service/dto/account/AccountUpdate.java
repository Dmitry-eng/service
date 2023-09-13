package com.service.dto.account;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountUpdate {

    private Long id;

    @NotNull
    private Boolean activated;
}
