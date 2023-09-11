package com.service.dto.account;

import com.service.other.marker.RepairUpdatePrice;
import com.service.other.marker.RepairUpdateStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RepairUpdate {

    @NotNull
    private Long id;

    private String fullDescription;

    private Long executor;

    @NotNull(groups = RepairUpdateStatus.class)
    private RepairStatus repairStatus;

    @NotNull(groups = RepairUpdatePrice.class)
    private BigDecimal price;
}
