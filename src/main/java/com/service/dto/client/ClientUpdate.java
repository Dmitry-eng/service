package com.service.dto.client;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class ClientUpdate {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    private String email;

    private String phoneNumber;

    @AssertFalse(message = "phoneNumber or email is required")
    public boolean isValidFields() {
        return StringUtils.isAllEmpty(phoneNumber, email);
    }
}
