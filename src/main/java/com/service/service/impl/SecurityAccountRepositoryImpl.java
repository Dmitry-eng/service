package com.service.service.impl;

import com.jwt.server.dto.authorization.Authorization;
import com.jwt.server.service.SecurityAccountRepository;
import com.service.mapper.AccountMapper;
import com.service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityAccountRepositoryImpl implements SecurityAccountRepository {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<Authorization> findByLogin(String login) {
        return accountRepository.findByLogin(login)
                .map(accountMapper::map);
    }
}
