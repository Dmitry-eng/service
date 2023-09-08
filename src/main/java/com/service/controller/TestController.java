package com.service.controller;

import com.service.dto.account.AccountCreate;
import com.service.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.*;

//@ConditionalOnExpression("${test-endpoint.enabled}")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final AccountService accountService;

    @PostMapping("/account/create")
    void createAccount(@RequestBody @Valid AccountCreate accountCreate) {
        accountService.createAccount(accountCreate);
    }
//    void createCompany();
//    void createRepair();
}
