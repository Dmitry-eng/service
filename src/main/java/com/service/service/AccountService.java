package com.service.service;

import com.service.dto.account.AccountCreate;
import com.service.dto.account.AccountInfo;
import com.service.entity.AccountEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import java.security.Security;
import java.util.List;

public interface AccountService {
    void createAccount(AccountCreate account);
    List<AccountInfo> findAllAccount();
    AccountEntity findById(Long id);
}
