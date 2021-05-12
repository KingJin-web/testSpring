package com.ex.tx.service;

import com.ex.tx.bean.Accounts;
import com.ex.tx.bean.OpRecord;
import com.ex.tx.bean.OpTypes;
import com.ex.tx.dao.AccountsDao;
import com.ex.tx.dao.OpRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
/*@Transactional(propagation = Propagation.REQUIRES_NEW,
        isolation = Isolation.DEFAULT,
        readOnly = false,
        timeout = 100,
        rollbackForClassName = {"RuntimeException"})*/

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountsDao accountsDao;
    @Autowired
    private OpRecordDao opRecordDao;

    @Override
    public Integer openAccount(Accounts account, double money) {
        //开户时存一条accounts记录
        account.setBalance(money);
        //saveAccount 获取新生成的账户的accountid
        int accountid = accountsDao.saveAccount(account);
        //开户时的日志
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(accountid);
        opRecord.setOpmoney(money);
        opRecord.setOptype(OpTypes.deposite.getName()); //用枚举做这个值(约束值),不容易出错
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));  //这里是一个Timestamp new Date().getTime() 取得一个long
        opRecord.setTransferid(" ");
        opRecordDao.saveOpRecord(opRecord);

        return accountid;
    }

    @Override
    public Accounts deposite(Accounts account, double money, String optype, String transferid) {
        //showBalance 返回的是一个Accounts对象
        Accounts a = this.showBalance(account);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);//用枚举做这个值(约束值),不容易出错
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));//这里是一个Timestamp new Date().getTime() 取得一个long
        if (transferid == null) {
            opRecord.setTransferid(" ");
        } else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);

        a.setBalance(a.getBalance() + money);
        accountsDao.updateAccount(a);

        return a;
    }

    @Override
    public Accounts withdraw(Accounts account, double money, String optype, String transferid) {
        Accounts a = this.showBalance(account);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);//用枚举做这个值(约束值),不容易出错
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));//这里是一个Timestamp new Date().getTime() 取得一个long
        if (transferid == null) {
            opRecord.setTransferid(" ");
        } else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);

        a.setBalance(a.getBalance() - money);
        accountsDao.updateAccount(a);

        return a;
    }

    @Override
    public Accounts transfer(Accounts inAccount, Accounts outAccount, double money) {
        String uid = UUID.randomUUID().toString();//转账流水
        this.deposite(inAccount, money, OpTypes.deposite.getName(), uid);
        Accounts newAccounts = this.withdraw(outAccount, money, OpTypes.withdraw.getName(), uid);
        return newAccounts;
    }

    @Override
    public Accounts showBalance(Accounts account) {
        return accountsDao.findAccount(account.getAccountId());
    }

    @Override
    public List<OpRecord> findById(Accounts account) {
        return opRecordDao.findByAccountid(account.getAccountId());
    }
}
