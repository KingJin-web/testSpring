package com.example.tx.dao;

import com.yc.tx.bean.OpRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-17 14:23
 */
@Repository
public class OpRecordDaoImpl implements OpRecordDao{

    //在dao中要使用，JdbcTemplate的模板对象，这个对象要通过dataSource创建
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOpRecord(OpRecord opRecord) {
        String sql="insert into oprecord(accountid,opmoney,optime,optype,transferid) values(?,?,?,?,?)";
        jdbcTemplate.update(connection -> {//lambda表达式
            PreparedStatement ps=connection.prepareStatement(sql);//第二参数的意思是返回这个字段生成的值
            ps.setInt(1,opRecord.getAccountId());
            ps.setDouble(2,opRecord.getOpmoney());
            ps.setTimestamp(3,opRecord.getOptime());
            ps.setString(4,opRecord.getOptype());
            ps.setString(5,opRecord.getTransferid());
            return ps;
        });

    }

    @Override
    public List<OpRecord> findAll() {
        String sql="select * from oprecord";
        List<OpRecord> list= this.jdbcTemplate.query(sql,(resultSet , rowNum)->{
            OpRecord a=new OpRecord();
            a.setId(resultSet.getInt("id"));
            a.setAccountId(resultSet.getInt("accountid"));
            a.setOpmoney(resultSet.getDouble("opmoney"));
            a.setOptime(resultSet.getTimestamp("optime"));
            a.setOptype(resultSet.getString("optype"));
            a.setTransferid(resultSet.getString("transferid"));
            return a;
        });
        return list;
    }

    @Override
    public List<OpRecord> findByAccountid(int accountid) {
        String sql="select * from oprecord where accountid=?";
        List<OpRecord> list= this.jdbcTemplate.query(sql,(resultSet , rowNum)->{
            OpRecord a=new OpRecord();
            a.setId(resultSet.getInt("id"));
            a.setAccountId(resultSet.getInt("accountid"));
            a.setOpmoney(resultSet.getDouble("opmoney"));
            a.setOptime(resultSet.getTimestamp("optime"));
            a.setOptype(resultSet.getString("optype"));
            a.setTransferid(resultSet.getString("transferid"));
            return a;
        },accountid);//多加了一个参数
        return list;
    }
}
