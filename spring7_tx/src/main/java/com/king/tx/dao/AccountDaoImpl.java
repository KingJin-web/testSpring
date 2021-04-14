package com.king.tx.dao;

import com.king.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-14 20:34
 */
@Service//给spring的类托管
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public AccountDaoImpl(DataSource dataSource) {
        System.out.println(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setDataSource(DataSource dataSource) {
        System.out.println(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int saveDataSource(Accounts account) {
        String sql = "insert into account(balance) values (?)";
        // KeyHolder keyHolder = new
        //jdbcTemplate.update()


        return 0;
    }
    @Autowired
    DataSource dataSource;
    @Override
    public List<Accounts> findAll() {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
    public Accounts updateAccount(Accounts account) {
        return null;
    }
}
