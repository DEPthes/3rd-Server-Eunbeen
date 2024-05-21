package com.example.springadvanced.domain.account;

import com.example.springadvanced.domain.account.application.AccountService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // AccountService 객체 생성
        AccountService accountService = new AccountService();

        // 두 개의 스레드 생성 및 실행
        Thread thread1 = new Thread(new AccountThread(accountService, new BigDecimal("100.00")));
        Thread thread2 = new Thread(new AccountThread(accountService, new BigDecimal("50.00")));

        thread1.start();
        thread2.start();
    }
}
