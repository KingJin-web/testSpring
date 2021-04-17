package com.king.tx.dao;

import com.king.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-14 20:34
 */
@Repository//给spring的类托管
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void setDataSource(DataSource dataSource) {
        System.out.println(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int saveDataSource(Accounts account) {
        String sql = "insert into accounts(balance) values ( ? )";

        //生成键的保存器
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //方案一匿名内部类
//        jdbcTemplate.update( new PreparedStatementCreator() {
//
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"accountid"});
//                //第二关参数的意思是返回这个字段生成的值
//                pstmt.setDouble(1, account.getBalance());
//                return pstmt;
//            }
//        }, keyHolder);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"accountid"});
            ps.setDouble(1, account.getBalance());
            return ps;
        }, keyHolder);

        // java.math.BigInteger cannot be cast to java.lang.Integer
        //return (Integer) keyHolder.getKey();
        //return ((BigInteger) keyHolder.getKey()).intValue();
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void delete(int accountid) {
        String sql = "delete from accounts where accountid = ?";
        this.jdbcTemplate.update(sql, accountid);
    }

    @Override
    public Accounts findAccount(int accountid) {
        String sql = "select * from accounts where accountid = ?";

        return this.jdbcTemplate.queryForObject(sql, (
                (resultSet, rowNum) -> {
                    Accounts a = new Accounts();
                    a.setAccountid(resultSet.getInt(1));
                    a.setBalance(resultSet.getDouble(2));
                    return a;
                }
        ),accountid);
    }


    @Override
    public List<Accounts> findAll() {

        // this.jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from accounts";

//        return this.jdbcTemplate.query(sql, new RowMapper<Accounts>() {
//            /**
//             *
//             * @param resultSet 结果集
//             * @param i 行号
//             * @return
//             * @throws SQLException
//             */
//            @Override
//            public Accounts mapRow(ResultSet resultSet, int i) throws SQLException {
//                Accounts a = new Accounts();
//                a.setAccountid(resultSet.getInt(1));
//                a.setBalance(resultSet.getDouble(2));
//
//                System.out.println(a);
//                return a;
//            }
//        });

        return this.jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    Accounts a = new Accounts();
                    a.setAccountid(resultSet.getInt(1));
                    a.setBalance(resultSet.getDouble(2));
                    return a;
                });


    }


    @Override
    public Accounts updateAccount(Accounts account) {
        String sql = "update accounts set balance = ? where accountid = ?";

        this.jdbcTemplate.update(sql, account.getBalance(), account.getAccountid());
        System.out.println("修改" + account + "成功");
        return account;
    }
}
