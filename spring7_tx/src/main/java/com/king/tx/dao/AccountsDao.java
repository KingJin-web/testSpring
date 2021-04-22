package com.king.tx.dao;

import com.king.tx.bean.Accounts;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountsDao {


    /**
     * 更新
     * @param account
     * @return
     */
    public Accounts updateAccount(Accounts account);

    /**
     * 开户
     * @param account
     * @return
     */
    public int saveDataSource(Accounts account);

    //查询所有
    public List<Accounts> findAll();

    //删除 by Id
    public void delete(int accountid);

    //查找 by Id
    public Accounts findAccount(int accountid);
}
