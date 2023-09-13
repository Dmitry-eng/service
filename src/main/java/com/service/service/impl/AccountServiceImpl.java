package com.service.service.impl;

import com.service.dto.account.AccountCreate;
import com.service.dto.account.AccountInfo;
import com.service.dto.account.AccountUpdate;
import com.service.entity.AccountEntity;
import com.service.exception.ServiceException;
import com.service.mapper.AccountMapper;
import com.service.repository.AccountRepository;
import com.service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.service.other.criteria.AccountUtils.findByValue;

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
    public List<AccountInfo> findAllAccount(String value) {
        List<AccountEntity> accountEntities = null;

        if (StringUtils.isEmpty(value)) {
            accountEntities = accountRepository.findAll();
        } else {
            accountEntities = accountRepository.findAll(findByValue(value));
        }

        return accountEntities
                .stream()
                .map(accountMapper::mapAccountInfo)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(AccountUpdate accountUpdate) {
        AccountEntity accountEntity = findById(accountUpdate.getId());
        accountEntity.setActivated(accountUpdate.getActivated());
        accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Account not found"));
    }
}
