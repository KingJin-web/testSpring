package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-14 21:10
 */
@Repository
public class AccountDaoImpl implements AccountDao{
    //在dao中要使用，JdbcTemplate的模板对象，这个对象要通过dataSource创建
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }
    @Override
    public int saveAccount(Accounts account) {
        String sql="insert into accounts(balance) values(?)";
        KeyHolder keyHolder=new GeneratedKeyHolder();//生成键的保存器
        jdbcTemplate.update(connection -> {//lambda表达式
            PreparedStatement ps=connection.prepareStatement(sql,new String[]{"accountid"});//第二参数的意思是返回这个字段生成的值
            ps.setDouble(1,account.getBalance());
            return ps;
        },keyHolder);
        return ((BigInteger)keyHolder.getKey()).intValue();
    }

    @Override
    public Accounts updateAccount(Accounts account) {
        String sql="update accounts set balance=? where accountid = ?";
        this.jdbcTemplate.update(sql,account.getBalance(),account.getAccountId());
        return account;
    }

    @Override
    public Accounts findAccount(int accountid) {
        String sql="select * from accounts where accountid=?";
        return this.jdbcTemplate.queryForObject(sql,
                (resultSet, rowNum) ->{
                    Accounts a=new Accounts();
                    a.setAccountId(resultSet.getInt("accountid"));
                    a.setBalance(resultSet.getDouble("balance"));
                    return a;
                }, accountid);
    }

    @Override
    public List<Accounts> findAll() {
        String sql="select * from accounts";
        List<Accounts> list= this.jdbcTemplate.query(sql,(resultSet ,rowNum)->{
            System.out.println("当前取的是第"+rowNum+"行的数据");
            Accounts a=new Accounts();
            a.setAccountId(resultSet.getInt("accountid"));
            a.setBalance(resultSet.getDouble("balance"));
            return a;
        });
        return list;
    }

    @Override
    public void delete(int accountid) {
        String sql="delete from accounts where accountid=?";
        this.jdbcTemplate.update(sql,accountid);

    }
}
