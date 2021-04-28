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

@Repository
public class AccountsDaoImpl implements AccountsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveAccount(Accounts account) {
        String sql = "insert into accounts(balance) values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder(); //生成键的保存器
        jdbcTemplate.update(connection -> { //lambda表达式
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"accountid"});//生成的accountid放入keyHolder
            ps.setDouble(1, account.getBalance());
            return ps;
        }, keyHolder);
        //方案一:用匿名内部类书写
        /*jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"accountid"});//第二个参数的意思是返回这个字段生成的值
                pstmt.setDouble(1, account.getBalance());
                return pstmt;
            }
        }, keyHolder);*/

        return ((BigInteger) keyHolder.getKey()).intValue();
    }

    @Override
    public Accounts updateAccount(Accounts account) {
        String sql = "update accounts set balance = ? where accountid = ?";
        int rows = this.jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());
        if (rows <= 0) {
            throw new RuntimeException("不存在该用户!");
        }
        return account;
    }

    @Override
    public Accounts findAccount(int accountid) {
        String sql = "select * from accounts where accountid = ?";

        return this.jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> {
                    Accounts a = new Accounts();
                    a.setAccountId(resultSet.getInt("accountid"));
                    a.setBalance(resultSet.getDouble("balance"));
                    return a;
                },
                accountid);
    }

    @Override
    public List<Accounts> findAll() {
        String sql = "select * from accounts";
        List<Accounts> list = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            System.out.println("当前取的是第" + rowNum + "行的数据");
            Accounts a = new Accounts();
            a.setAccountId(resultSet.getInt("accountid"));
            a.setBalance(resultSet.getDouble("balance"));
            return a;
        });

        /*List<Accounts> list = this.jdbcTemplate.query(sql, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                System.out.println("当前取的是第" + rowNum + "行的数据");
                Accounts a = new Accounts();
                a.setAccountId(resultSet.getInt("accountid"));
                a.setBalance(resultSet.getDouble("balance"));
                return a;
            }
        });*/
        return list;
    }

    @Override
    public void delete(int accountid) {
        String sql = "delete from accounts where accountid = ?";
        this.jdbcTemplate.update(sql, accountid);
    }
}
