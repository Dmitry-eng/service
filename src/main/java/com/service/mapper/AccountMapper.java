package com.service.mapper;

import com.jwt.server.dto.authorization.Account;
import com.service.dto.account.AccountCreate;
import com.service.dto.account.AccountInfo;
import com.service.entity.AccountEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Mapper
public abstract class AccountMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "password", ignore = true)
    public abstract AccountEntity map(AccountCreate accountCreate);

    public abstract Account map(AccountEntity entity);

    public abstract AccountInfo mapAccountInfo(AccountEntity value);

    @AfterMapping
    public void afterMapping(@MappingTarget AccountEntity accountEntity, AccountCreate accountCreate) {
        accountEntity.setIsEnabled(true);
        accountEntity.setDateCreated(LocalDateTime.now());

        String password = passwordEncoder.encode(accountCreate.getPassword());

        accountEntity.setPassword(password);
    }
}
