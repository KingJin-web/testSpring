package com.yc.dao;

import com.yc.bean.ResUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResUserMapperImp implements ResUserMapper{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int QueryUserByUsernameAndPwd(ResUser resUser) {
        String sql="SELECT\n" +
                "	count(*) cnt\n" +
                "FROM\n" +
                "	resuser\n" +
                "WHERE\n" +
                "	username = ?" +
                "AND pwd =?";
        List<Integer> list = jdbcTemplate.query(sql, new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, resUser.getUsername(), resUser.getPwd());
        System.out.println("--"+sql+"--");
        System.out.println("=---=-=-"+list.get(0));
        return list.get(0);
    }

    @Override
    public int queruserid(ResUser resUser) {
        String sql="SELECT userid from resuser where username=? and pwd=?";
        List<Integer> list = jdbcTemplate.query(sql, new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }, resUser.getUsername(), resUser.getPwd());
        System.out.println("--"+sql+"--");
        return list.get(0);
    }

}
