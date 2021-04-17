package com.king.tx.bean;

import com.king.tx.dao.AccountDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: testSpring
 * @description: 事物处理
 * @author: King
 * @create: 2021-04-17 16:27
 */
@Service
@Transactional
public class TransactionalImpl implements AccountDao {

    @Override
    public Accounts updateAccount(Accounts account) {
        return null;
    }

    @Override
    public int saveDataSource(Accounts account) {
        return 0;
    }

    @Override
    public List<Accounts> findAll() {
        return null;
    }

    @Override
    public void delete(int accountid) {

    }

    @Override
    public Accounts findAccount(int accountid) {
        return null;
    }
}
