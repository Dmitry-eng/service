package com.service.service.impl;

import com.jwt.server.dto.authorization.Authorization;
import com.jwt.server.service.CompanyRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityCompanyRepository implements CompanyRepository {
    @Override
    public Optional<Authorization> findByLogin(String login) {
        return Optional.empty();
    }
}
