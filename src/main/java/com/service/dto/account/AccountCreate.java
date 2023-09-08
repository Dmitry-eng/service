package com.service.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountCreate {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    private String email;

    @NotBlank
    private String name;
}
