package com.service.controller;

import com.service.dto.account.AccountCreate;
import com.service.dto.account.AccountInfo;
import com.service.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public void createAccount(@RequestBody @Valid AccountCreate accountCreate) {
        accountService.createAccount(accountCreate);
    }

    @GetMapping("/all")
    public List<AccountInfo> findAllAccount() {
        return accountService.findAllAccount();
    }

}