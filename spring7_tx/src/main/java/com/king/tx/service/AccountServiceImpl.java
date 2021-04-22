package com.king.tx.service;

import com.king.tx.bean.Accounts;
import com.king.tx.bean.OpRecord;

import com.king.tx.bean.Optypes;

import com.king.tx.dao.AccountsDao;
import com.king.tx.dao.AccountsDaoImpl;
import com.king.tx.dao.OpRecordDao;
import com.king.tx.dao.OpRecordDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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

@Repository
public class AccountServiceImpl implements AccountService {
    @Autowired
    private OpRecordDaoImpl opRecordDao;

    @Autowired
    private AccountsDaoImpl accountsDao;

//    @Autowired
//    public void setOpRecordDao(OpRecordDao opRecordDao) {
//        this.opRecordDao = opRecordDao;
//    }
//
//    @Autowired
//    public void setAccountDao(AccountsDao accountsDao) {
//        this.accountsDao = accountsDao;
//    }

    @Override
    public Integer openAccount(Accounts account, double money) {
        System.out.println(accountsDao);
        account.setBalance(money);
        int accountid = accountsDao.saveDataSource(account);
        //开户时的日志
        OpRecord opRecord = new OpRecord();

        opRecord.setAccountid(1);
        opRecord.setOpmoney(1.0);
        opRecord.setTransferid(Optypes.deposite.getName());
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        opRecord.setTransferid(" ");
        opRecordDao.saveOpRecord(opRecord);
        return accountid;

    }

    @Override
    @Transactional
    public Accounts deposite(Accounts accounts, double money, String optype, String transferid) {
        Accounts a = this.showBalance(accounts);
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountid());
        opRecord.setOpmoney(money);
        opRecord.setTransferid(optype);
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));


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
    @Transactional
    public Accounts withdraw(Accounts accounts, double money, String optype, String transferid) {
        Accounts a = this.showBalance(accounts);
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountid());
        opRecord.setOpmoney(money);
        opRecord.setTransferid(optype);
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));


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
    public Accounts transfer(Accounts inAccounts, Accounts outAccounts, double money) {

        String uid = UUID.randomUUID().toString();
        System.out.println("uid" + uid);
        this.deposite(inAccounts, money, Optypes.transfer.getName(), uid);
        return this.withdraw(outAccounts, money, Optypes.transfer.getName(), uid);

    }

    @Override
    @Transactional(readOnly = true)
    public Accounts showBalance(Accounts accounts) {

        return accountsDao.findAccount(accounts.getAccountid());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpRecord> findById(Accounts accounts) {
        //accountsDao.findAll();
        return opRecordDao.findById2(accounts.getAccountid());
    }
}
