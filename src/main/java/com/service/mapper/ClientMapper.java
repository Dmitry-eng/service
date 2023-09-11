package com.service.mapper;

import com.service.dto.account.Client;
import com.service.dto.account.ClientCreate;
import com.service.entity.ClientEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper
public abstract class ClientMapper {

    public abstract ClientEntity map(ClientCreate clientCreate);
    public abstract Client map(ClientEntity entity);

    @AfterMapping
    protected void afterMapping(@MappingTarget ClientEntity clientEntity, ClientCreate clientCreate) {
        clientEntity.setCreateDate(LocalDateTime.now());
    }

}
