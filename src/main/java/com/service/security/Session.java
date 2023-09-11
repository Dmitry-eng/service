package com.service.security;

import com.jwt.server.dto.JwtAuthentication;
import com.service.entity.AccountEntity;
import com.service.exception.ServiceException;
import com.service.repository.AccountRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Data
public class Session {

    private final AccountRepository accountRepository;

    public AccountEntity getAccountSession() {
        String login = Optional.of(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(authentication -> (JwtAuthentication) authentication)
                .map(JwtAuthentication::getUsername)
                .orElseThrow(() -> new ServiceException("Login not found"));

        return accountRepository.findByLogin(login)
                .orElseThrow(() ->  new ServiceException("Account not found"));
    }
}
