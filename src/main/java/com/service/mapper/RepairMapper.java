package com.service.mapper;

import com.service.dto.account.RepairCreate;
import com.service.dto.account.RepairStatus;
import com.service.dto.account.ShortInfoRepair;
import com.service.entity.RepairEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper
public abstract class RepairMapper {

    public abstract RepairEntity map(RepairCreate repairCreate);

    @Mapping(target = "executor", source = "repairEntity.executor.name")
    public abstract ShortInfoRepair map(RepairEntity repairEntity);

    @AfterMapping
    protected void afterMapping(@MappingTarget RepairEntity entity, RepairCreate repairCreate) {
        entity.setDateCreated(LocalDateTime.now());
        entity.setStatus(RepairStatus.NEW.name());
    }
}
