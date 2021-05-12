package com.ex.tx.dao;


import com.ex.tx.bean.Accounts;

import java.util.List;

public interface AccountsDao {

    /**
     * 开户
     *
     * @param account
     * @return
     */
    public int saveAccount(Accounts account);

    /**
     * 更新
     *
     * @param account
     * @return
     */
    public Accounts updateAccount(Accounts account);

    /**
     * 根据id查找账户
     *
     * @param accountid
     * @return
     */
    public Accounts findAccount(int accountid);

    /**
     * 查找所有账户
     *
     * @return
     */
    public List<Accounts> findAll();

    /**
     * 根据id删除账户
     *
     * @param accountid
     */
    public void delete(int accountid);

}
