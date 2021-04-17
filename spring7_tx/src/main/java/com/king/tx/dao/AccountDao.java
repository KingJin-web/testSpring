package com.king.tx.dao;

import com.king.tx.bean.Accounts;

import java.util.List;


public interface AccountDao {
//    public int setDataSource(Accounts account);

    //更新
    public Accounts updateAccount(Accounts account);

    /**
     * 开户
     * @param account
     * @return
     */
    public Integer saveDataSource(Accounts account);

    //查询所有
    public List<Accounts> findAll();

    public void delete();
}
