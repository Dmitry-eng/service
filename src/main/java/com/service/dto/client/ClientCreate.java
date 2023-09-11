package com.service.dto.client;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class ClientCreate {

    @NotBlank
    private String name;

    private String phoneNumber;

    private String email;

    @AssertFalse(message = "phoneNumber or email is required")
    public boolean isValidFields(){
        return StringUtils.isAllEmpty(phoneNumber, email);
    }

}
