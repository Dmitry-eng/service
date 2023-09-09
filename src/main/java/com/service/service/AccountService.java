package com.service.service;

import com.service.dto.account.AccountCreate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import java.security.Security;

public interface AccountService {
    void createAccount(AccountCreate account);
}
