package com.yc.tx.Service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.AccountDao;
import com.yc.tx.dao.OpRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-17 16:25
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED,
                isolation = Isolation.DEFAULT,
                readOnly = false,
                timeout = 100,
                rollbackForClassName = {"RuntimeException"})
public class AccountServiceImpl implements AccountService{

    @Resource
    private AccountDao accountDao;

    @Autowired
    private OpRecordDao opRecordDao;
    @Override
    public Integer openAccount(Accounts account, double money) {
        account.setBalance(money);
        int accountid=accountDao.saveAccount(account);
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountId(1);
        opRecord.setOpmoney(1.0);
        opRecord.setOptype(OpTypes.deposite.getName());
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        opRecord.setTransferid(" ");
        opRecordDao.saveOpRecord(opRecord);
        return accountid;
    }

    @Override
    public Accounts deposite(Accounts account, double money, String optype, String transferid) {
        Accounts a=this.showBalance(account);

        OpRecord opRecord=new OpRecord();
        opRecord.setAccountId(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        if(transferid==null){
            opRecord.setTransferid(" ");
        }else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);
        a.setBalance(a.getBalance()+money);
        accountDao.updateAccount(a);
        return a;
    }


    @Override
    @Transactional
    public Accounts withdraw(Accounts account, double money,String optype,String transferid) {
        Accounts a=this.showBalance(account);

        OpRecord opRecord=new OpRecord();
        opRecord.setAccountId(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        if(transferid==null){
            opRecord.setTransferid(" ");
        }else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);
        a.setBalance(a.getBalance()-money);
        accountDao.updateAccount(a);
        return a;
    }

    @Override
    public Accounts transfer(Accounts inAccount, Accounts outAccount, double money) {
        String uid= UUID.randomUUID().toString();
        this.deposite(inAccount,money, OpTypes.transfer.getName(),uid);
        Accounts newAccounts=this.withdraw(outAccount,money,OpTypes.transfer.getName(),uid);
        return newAccounts;
    }

    @Override
    @Transactional(readOnly = true)
    public Accounts showBalance(Accounts account) {
        return accountDao.findAccount(account.getAccountId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpRecord> findById(Accounts account) {
        return opRecordDao.findByAccountid(account.getAccountId());
    }
}
