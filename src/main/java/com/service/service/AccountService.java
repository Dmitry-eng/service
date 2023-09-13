package com.service.service;

import com.service.dto.account.AccountCreate;
import com.service.dto.account.AccountInfo;
import com.service.dto.account.AccountUpdate;
import com.service.entity.AccountEntity;
import java.util.List;

public interface AccountService {
    void createAccount(AccountCreate account);
    List<AccountInfo> findAllAccount(String value);
    AccountEntity findById(Long id);
    void updateStatus(AccountUpdate accountUpdate);
}
