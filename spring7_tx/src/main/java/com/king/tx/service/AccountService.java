package com.king.tx.service;

import com.king.tx.bean.Accounts;
import com.king.tx.bean.OpRecord;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountService {
    Integer openAccount(Accounts account, double money);

    Accounts deposite(Accounts accounts, double money);

    Accounts withdraw(Accounts accounts, double money);

    Accounts transfer(Accounts accounts, double money);

    @Transactional(readOnly = true)
    Accounts showBalance(Accounts accounts);

    @Transactional(readOnly = true)
    List<OpRecord> findById(Accounts accounts);
}
