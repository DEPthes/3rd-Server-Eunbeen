package com.example.springadvanced.domain.account.application;

import com.example.springadvanced.domain.account.domain.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class AccountService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateBalance(Long accountId, BigDecimal amount) {
        Account account = entityManager.find(Account.class, accountId);
        BigDecimal newBalance = account.getBalance().add(amount);
        account.updateBalance(newBalance);
    }
}
