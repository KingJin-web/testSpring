package com.example.tx.dao;

import com.example.tx.bean.Accounts;

import java.util.List;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-14 20:20
 */
public interface AccountDao {

    int saveAccount(Accounts account);
    Accounts updateAccount(Accounts account);
    Accounts findAccount(int accountid);
    List<Accounts> findAll();
    void delete(int accountid);

}
