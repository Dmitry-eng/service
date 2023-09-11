package com.service.dto.repair;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RepairCreate {

    @NotBlank
    private String shortDescription;

    private String address;

    private String contactPhoneNumber;

    @NotNull
    private Long clientId;

}