package com.service.controller;

import com.service.dto.account.AccountCreate;
import com.service.dto.account.AccountInfo;
import com.service.dto.account.AccountUpdate;
import com.service.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public void updateStatus(@RequestBody @Valid AccountUpdate accountUpdate) {
        accountService.updateStatus(accountUpdate);
    }

    @GetMapping({"/all", "/all/{value}"})
    public List<AccountInfo> findAllAccount(@PathVariable(required = false) String value) {
        return accountService.findAllAccount(value);
    }

}