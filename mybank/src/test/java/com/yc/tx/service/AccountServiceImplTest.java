package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest

public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional  //在junit中通过使用 @Transactional 在测试完后，用来恢复现场
    public void openAccount() {
        Integer accountid = accountService.openAccount(new Accounts(), 1);
        System.out.println(accountid);
        Assert.assertNotNull(accountid);
    }

    @Test
    public void deposite() {
        Accounts a = new Accounts();
        a.setAccountId(5);
        Accounts aa = accountService.deposite(a, 100, OpTypes.deposite.getName(), null);
        System.out.println(aa);
    }

    @Test
    public void withdraw() {
        Accounts a = new Accounts();
        a.setAccountId(5);
        Accounts aa = accountService.withdraw(a, 5, OpTypes.withdraw.getName(), null);
        System.out.println(aa);
    }

    @Test
    public void transfer() {
        Accounts out = new Accounts();
        out.setAccountId(5);

        Accounts inA = new Accounts();
        inA.setAccountId(6);

        this.accountService.transfer(inA, out, 2);
    }

    @Test
    public void showBalance() {
        Accounts a = new Accounts();
        a.setAccountId(5);
        a = this.accountService.showBalance(a);
        System.out.println(a);
    }

    @Test
    public void findById() {
    }
}