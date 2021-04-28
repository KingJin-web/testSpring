package com.yc.tx.dao;

import com.yc.tx.bean.OpRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class OpRecordDaoImpl implements OpRecordDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOpRecord(OpRecord opRecord) {
        String sql = "insert into oprecord(accountid,opmoney,optime,optype,transferid) values (?,?,?,?,?);";

        this.jdbcTemplate.update(connection -> {//lambda表达式
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, opRecord.getAccountid());
            ps.setDouble(2, opRecord.getOpmoney());
            ps.setTimestamp(3, opRecord.getOptime());
            ps.setString(4, opRecord.getOptype());
            ps.setString(5, opRecord.getTransferid());
            return ps;
        });
    }

    @Override
    public List<OpRecord> findAll() {
        String sql = "select * from oprecord";
        return this.jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            OpRecord o = new OpRecord();
            o.setId(resultSet.getInt("id"));
            o.setAccountid(resultSet.getInt("accountid"));
            o.setOpmoney(resultSet.getDouble("opmoney"));
            o.setOptime(resultSet.getTimestamp("optime"));//timestamp
            o.setOptype(resultSet.getString("optype"));
            o.setTransferid(resultSet.getString("transferid"));
            return o;
        });
    }

    @Override
    public List<OpRecord> findByAccountid(int accountid) {
        String sql = "select * from oprecord where accountid = ?";

        List<OpRecord> list = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            OpRecord o = new OpRecord();
            o.setId(resultSet.getInt("id"));
            o.setAccountid(resultSet.getInt("accountid"));
            o.setOpmoney(resultSet.getDouble("opmoney"));
            o.setOptime(resultSet.getTimestamp("optime"));//timestamp
            o.setOptype(resultSet.getString("optype"));
            o.setTransferid(resultSet.getString("transferid"));
            return o;
        }, accountid);
        return list;
    }


}

