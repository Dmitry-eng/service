package com.service.dto.account;

import jakarta.validation.constraints.NotBlank;

public class RepairCreate {

    @NotBlank
    private String shortDescription;
    private String fullDescription;

}
