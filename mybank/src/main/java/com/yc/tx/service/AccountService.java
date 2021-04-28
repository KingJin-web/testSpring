package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;

import java.util.List;

public interface AccountService {

    /**
     * 开户
     *
     * @param account
     * @param money
     * @return
     */
    public Integer openAccount(Accounts account, double money);

    /**
     * 存钱
     *
     * @param account
     * @param money
     * @return
     */
    public Accounts deposite(Accounts account, double money, String optype, String transferid);

    /**
     * 取钱
     *
     * @param account
     * @param money
     * @return
     */
    public Accounts withdraw(Accounts account, double money, String optype, String transferid);

    /**
     * 转账
     *
     * @param inAccount  转入账户
     * @param outAccount 转出账户
     * @param money
     * @return
     */
    public Accounts transfer(Accounts inAccount, Accounts outAccount, double money);

    /**
     * 查看余额
     *
     * @param account
     * @return
     */
    public Accounts showBalance(Accounts account);

    /**
     * 查看日志
     *
     * @param account
     * @return
     */
    public List<OpRecord> findById(Accounts account);


}
