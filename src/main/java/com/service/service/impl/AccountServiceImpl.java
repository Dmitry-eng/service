package com.service.service.impl;

import com.service.dto.account.AccountCreate;
import com.service.entity.AccountEntity;
import com.service.mapper.AccountMapper;
import com.service.repository.AccountRepository;
import com.service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public void createAccount(AccountCreate account) {
        AccountEntity accountEntity = accountMapper.map(account);
        accountRepository.save(accountEntity);
    }
}
