package com.service.service.impl;

import com.service.dto.account.AccountCreate;
import com.service.dto.account.AccountInfo;
import com.service.entity.AccountEntity;
import com.service.exception.ServiceException;
import com.service.mapper.AccountMapper;
import com.service.repository.AccountRepository;
import com.service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AccountInfo> findAllAccount() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::mapAccountInfo)
                .collect(Collectors.toList());
    }

    @Override
    public AccountEntity findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Account not found"));
    }
}
