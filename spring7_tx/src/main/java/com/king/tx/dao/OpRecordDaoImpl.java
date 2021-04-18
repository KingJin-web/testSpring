package com.king.tx.dao;

import com.king.tx.bean.OpRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-17 13:33
 */
@Repository
public class OpRecordDaoImpl implements OpRecordDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveOpRecord(OpRecord opRecord) {
        String sql = "INSERT INTO `mybatis`.`oprecord`(`accountid`, `opmoney`, `optime`, `optype`, `transferid`) VALUES (?, ?, ?, ?, ?);";

        //生成键的保存器
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, opRecord.getAccountid());
            ps.setDouble(2, opRecord.getOpmoney());
            ps.setTimestamp(3, opRecord.getOptime());
            ps.setString(4, opRecord.getOptype());
            ps.setString(5, opRecord.getTransferid());
            return ps;
        }, keyHolder);
        System.out.println(opRecord);
        // java.math.BigInteger cannot be cast to java.lang.Integer
        //return (Integer) keyHolder.getKey();
        //return ((BigInteger) keyHolder.getKey()).intValue();
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public List<OpRecord> findAll() {

        String sql = "select * from oprecord";
        return this.jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    OpRecord o = new OpRecord();
                    o.setId(resultSet.getInt(1));
                    o.setAccountid(resultSet.getInt(2));
                    o.setOpmoney(resultSet.getDouble(3));
                    o.setOptime(resultSet.getTimestamp(4));
                    o.setOptype(resultSet.getString(5));
                    o.setTransferid(resultSet.getString(6));
                    return o;
                });
    }

    @Override
    public OpRecord findById(int id) {
        String sql = "select * from oprecord where id = ?";
        return this.jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> {
                    OpRecord o = new OpRecord();
                    o.setId(resultSet.getInt(1));
                    o.setAccountid(resultSet.getInt(2));
                    o.setOpmoney(resultSet.getDouble(3));
                    o.setOptime(resultSet.getTimestamp(4));
                    o.setOptype(resultSet.getString(5));
                    o.setTransferid(resultSet.getString(6));
                    return o;
                }, id);
    }
    @Override
    public List<OpRecord> findById2(int id) {
        String sql = "select * from oprecord where id = ?";
        return this.jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    OpRecord o = new OpRecord();
                    o.setId(resultSet.getInt(1));
                    o.setAccountid(resultSet.getInt(2));
                    o.setOpmoney(resultSet.getDouble(3));
                    o.setOptime(resultSet.getTimestamp(4));
                    o.setOptype(resultSet.getString(5));
                    o.setTransferid(resultSet.getString(6));
                    return o;
                }, id);
    }
}
