package com.king.tx.dao;

import com.king.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public Integer saveDataSource(Accounts account) {
        String sql = "insert into accounts(balance) values ( ? )";

        //生成键的保存器
        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(sql, new PreparedStatementCreator() {
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
        System.out.println(keyHolder.toString());

        // java.math.BigInteger cannot be cast to java.lang.Integer
        //return (Integer) keyHolder.getKey();
    }


    @Override
    public List<Accounts> findAll() {

        // this.jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from accounts";
        List<Accounts> list = this.jdbcTemplate.query(sql, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet resultSet, int i) throws SQLException {
                Accounts a = new Accounts();
                a.setAccountid(resultSet.getInt(1));
                a.setBalance(resultSet.getDouble(2));

                System.out.println(a);
                return a;
            }
        });

        return list;
    }

    @Override
    public void delete() {

    }

    @Override
    public Accounts updateAccount(Accounts account) {
        return null;
    }
}
