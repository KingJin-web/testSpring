package com.yc.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.Accounts;
import com.yc.tx.dao.AccountDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-14 19:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class })
public class TestDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccountDao accountDao;

    //数据源的测试
    @Test//测试用例上加@Test注解，并且用例类名为testXXX
    public void testDataSource() throws SQLException {
        Assert.assertNotNull(dataSource);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testAccountsDaoImpl() {
        Assert.assertNotNull(accountDao);
    }

    @Test
    public void testOpenAccounts() {
        Accounts a=new Accounts();
        a.setBalance(10.0);
        int accountid = accountDao.saveAccount(a);
        System.out.println("开户成功"+accountid);

    }
    @Test
    public void testFindAll() {
        List<Accounts>list=this.accountDao.findAll();
        System.out.println(list);

    }

    @Test
    public void testFindByid() {
        Accounts a=this.accountDao.findAccount(4);
        System.out.println(a);
    }

}
