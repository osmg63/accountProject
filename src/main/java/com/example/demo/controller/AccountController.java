package com.example.demo.controller;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.CreateAccountRequest;
import com.example.demo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/account")
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody CreateAccountRequest request){
        return  ResponseEntity.ok(accountService.createAccount(request));
    }

}
