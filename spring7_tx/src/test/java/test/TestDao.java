package test;

import com.king.tx.AppConfig;
import com.king.tx.dao.AccountDaoImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-14 20:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class })
public class TestDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccountDaoImpl accountDao;

    @Test//测试用例上加@Test注解，并且用例类名为testXXX
    public void testDataSource() throws SQLException {
        Assert.assertNotNull(dataSource);
        System.out.println(dataSource.getConnection().toString());
    }

    @Test
    public void testFindAll(){
        System.out.println(accountDao.findAll());
    }
}
