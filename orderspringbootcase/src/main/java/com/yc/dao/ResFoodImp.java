package com.yc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class ResFoodImp implements ResFood{
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 获取进入所有菜品的信息
     * @return
     */
    @Override
    public List<Map<String, Object>> queryallfoodinfo() {
        String sql="select * from resfood ";
        System.out.println("---"+sql+"---");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    /**
     * 这是对菜品进行每页  每页5个
     * @param nowpage
     * @return
     */
    @Override
    public List<Map<String, Object>> queryallfoodinfofen(Integer nowpage) {
        Integer page=(nowpage-1)*5;
        String sql="select * from resfood LIMIT ?,5";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, page);
        return maps;
    }

    /**
     * 查询菜品个数
     * @return
     */
    @Override
    public Integer querycountfoods() {
        String sql="select count(*) cnt from resfood";
        List<Integer> list = jdbcTemplate.query(sql, new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        });
        return list.get(0);
    }


    /**
     * 获取某一个菜品的详细信息  用户进入详情页
     * 跳转
     * @param fid
     * @return
     */
    @Override
    public Map<String, Object> queryonrfooddetail(String fid) {
        String sql="select * from resfood where fid=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, fid);
        return map;
    }

    /**
     * 将菜品填加至购物车中
     * @param map
     * @return
     */
    @Override
    public int addfoodtocar(Map map) {
        String sql="insert into resorderitemtemp VALUES(NULL,?,?,?,?)";
        int i = jdbcTemplate.update(sql, map.get("fid"),
                map.get("num"),
                map.get("time"),
                map.get("uid"));

        return i;
    }

    @Override
    public int queronefid(Map map) {
        String sql="select count(*)cnt from resorderitemtemp where fid=? and userid=? ";
        List<Integer> list = jdbcTemplate.query(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        },map.get("fid"),map.get("uid"));
        return list.get(0);
    }

    @Override
    public int updatefoodnum(Map map) {
        String sql="update resorderitemtemp SET num=? where fid=? and userid=? ";
        int i = jdbcTemplate.update(sql,map.get("num2") ,map.get("fid"), map.get("uid"));
        return i;
    }

    @Override
    public int queryonefidnum(Map map) {
        String sql="select num from resorderitemtemp where fid=? and userid=?";
        List<Integer> list = jdbcTemplate.query(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        },map.get("fid"),map.get("uid"));
        return list.get(0);
    }

}
