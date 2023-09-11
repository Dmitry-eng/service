package com.service.mapper;

import com.service.dto.repair.FullInfoRepair;
import com.service.dto.repair.RepairCreate;
import com.service.dto.repair.RepairStatus;
import com.service.dto.repair.ShortInfoRepair;
import com.service.entity.RepairEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(uses = CommentMapper.class)
public abstract class RepairMapper {

    @Mapping(target = "phoneNumber", source = "repairCreate.contactPhoneNumber")
    public abstract RepairEntity map(RepairCreate repairCreate);

    @Mapping(target = "executor", source = "repairEntity.executor.name")
    public abstract ShortInfoRepair map(RepairEntity repairEntity);

    @Mapping(target = "contactPhoneNumber", source = "repairEntity.phoneNumber")
    @Mapping(target = "comments", source = "comments")
    public abstract FullInfoRepair mapToFullInfoRepair(RepairEntity repairEntity);

    @AfterMapping
    protected void afterMapping(@MappingTarget RepairEntity entity, RepairCreate repairCreate) {
        entity.setDateCreated(LocalDateTime.now());
        entity.setStatus(RepairStatus.NEW);
    }

}
