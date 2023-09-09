package com.service.controller;

import com.jwt.server.config.filter.AccountJwtFilter;
import com.service.dto.account.AccountCreate;
import com.service.dto.account.RepairCreate;
import com.service.service.AccountService;
import com.service.service.RepairService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.web.bind.annotation.*;

//@ConditionalOnExpression("${test-endpoint.enabled}")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final AccountService accountService;
    private final RepairService repairService;
    @PostMapping("/account/create")
    void createAccount(@RequestBody @Valid AccountCreate accountCreate) {
        accountService.createAccount(accountCreate);
    }

    @PostMapping("/repair/create")
    public void createRepair(@RequestBody @Valid RepairCreate repairCreate) {
        repairService.createRepair(repairCreate);
    }
}
