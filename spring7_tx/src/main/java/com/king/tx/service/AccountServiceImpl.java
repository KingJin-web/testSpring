package com.king.tx.service;

import com.king.tx.bean.Accounts;
import com.king.tx.bean.OpRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-17 16:45
 */
@Service
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        timeout = 100,
        rollbackForClassName = {"RuntimeException"}
)
public class AccountServiceImpl implements AccountService {
    @Override
    public Integer openAccount(Accounts account, double money) {
        return null;
    }

    @Override
    public Accounts deposite(Accounts accounts, double money){
        return null;
    }

    @Override
    public Accounts withdraw(Accounts accounts, double money){
        return null;
    }

    @Override
    public Accounts transfer(Accounts accounts, double money){
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Accounts showBalance(Accounts accounts){
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpRecord> findById(Accounts accounts){
        return null;
    }
}
