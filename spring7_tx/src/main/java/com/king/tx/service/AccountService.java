package com.king.tx.service;

import com.king.tx.bean.Accounts;
import com.king.tx.bean.OpRecord;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author King
 */
public interface AccountService {
    //开户
    Integer openAccount(Accounts account, double money);

    //存钱
    public Accounts deposite(Accounts accounts, double money, String optype, String transferid);

    //取钱
    Accounts withdraw(Accounts accounts, double money, String optype, String transferid);

    //转账
    Accounts transfer(Accounts inAccounts, Accounts outAccounts, double money);

    //查看余额
    @Transactional(readOnly = true)
    Accounts showBalance(Accounts accounts);

    //查看日志
    @Transactional(readOnly = true)
    List<OpRecord> findById(Accounts accounts);
}
