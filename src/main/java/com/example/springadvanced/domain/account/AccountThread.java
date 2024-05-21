package com.example.springadvanced.domain.account;

import com.example.springadvanced.domain.account.application.AccountService;

import java.math.BigDecimal;

public class AccountThread implements Runnable {
    private AccountService accountService;
    private BigDecimal amount;

    public AccountThread(AccountService accountService, BigDecimal amount) {
        this.accountService = accountService;
        this.amount = amount;
    }

    @Override
    public void run() {
        accountService.updateBalance(1L, amount);
    }
}