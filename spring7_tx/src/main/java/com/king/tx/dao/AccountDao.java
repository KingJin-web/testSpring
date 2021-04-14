package com.king.tx.dao;

import com.king.tx.bean.Accounts;

import java.util.List;


public interface AccountDao {
//    public int setDataSource(Accounts account);

    public Accounts updateAccount(Accounts account);

    public int saveDataSource(Accounts account);

    public List<Accounts> findAll();
}
