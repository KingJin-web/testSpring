package com.yc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class OrderMapperImp implements OrderMapper{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取生成订单的自增id
     */
    @Override
    public int createorder(Map map) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT into resorder(roid,userid,address,tel,ordertime,ps,status) VALUES (null,?,?,?,?,?,0)";
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,map.get("uid"));
            ps.setObject(2,map.get("address"));
            ps.setObject(3,map.get("tel"));
            ps.setObject(4,map.get("time"));
            ps.setObject(5,map.get("ps"));
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        int roid = keyHolder.getKey().intValue();//获取最终插入的自增的id
        return roid;
    }

    /**
     * 根据多选框得到菜品id 得到对应的菜品信息
     * @param roitid 购物车中的菜品id
     * @return
     */
    @Override
    public Map<String, Object> querycheckedpro(String roitid) {
        String sql="SELECT * from resorderitemtemp a LEFT JOIN resfood b on a.fid=b.fid where roitid=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, roitid);
        return map;
    }

    /**
     * 获取某个用户购物车中的菜品信息
     * @param userid
     * @return
     */
    @Override
    public List<Map<String, Object>> querycartfoodsinfo(String userid) {
        String sql="SELECT\n" +
                "	a.roitid,\n" +
                "	b.fname,\n" +
                "	b.realprice,\n" +
                "	a.num,\n" +
                "	(b.realprice * a.num) total\n" +
                "FROM\n" +
                "	resorderitemtemp a\n" +
                "LEFT JOIN resfood b ON a.fid = b.fid\n" +
                "WHERE\n" +
                "	a.userid = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, userid);
        return maps;
    }

    /**
     *
     * @param userid
     * @param roitid
     * @return
     */
    @Override
    public int updcartfoodsnum(String userid, String roitid) {
        String sql="UPDATE resorderitemtemp\n" +
                "SET num = num - 1\n" +
                "WHERE\n" +
                "	roitid = ?\n" +
                "AND userid = ?";
        int i = jdbcTemplate.update(sql, roitid, userid);
        return i;
    }

    public int updcartfoodsnum2(String userid, String roitid) {
        String sql="UPDATE resorderitemtemp\n" +
                "SET num = num + 1\n" +
                "WHERE\n" +
                "	roitid = ?\n" +
                "AND userid = ?";
        int i = jdbcTemplate.update(sql, roitid, userid);
        return i;
    }

    @Override
    public int querynownum(String userid, String roitid) {
        String sql="SELECT\n" +
                "	num\n" +
                "FROM\n" +
                "	resorderitemtemp\n" +
                "WHERE\n" +
                "	roitid = ?\n" +
                "AND userid = ?";
        List<Integer> list = jdbcTemplate.query(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        },roitid,userid);
        return list.get(0);
    }

    @Override
    public int delcartinfo(String roitid) {
        String sql="DELETE from resorderitemtemp where roitid=?";
        int i = jdbcTemplate.update(sql, roitid);
        return i;
    }

    /**
     * 生成订单明细记录
     * @param map
     * @return
     */
    public int createorderitem(Map map,String roitid){
        String sql="INSERT INTO resorderitem SELECT\n" +
                "	NULL,\n" +
                "	?,\n" +
                "	a.fid,\n" +
                "	(b.realprice * a.num) dealprice,\n" +
                "	a.num\n" +
                "FROM\n" +
                "	resorderitemtemp a\n" +
                "LEFT JOIN resfood b ON a.fid = b.fid\n" +
                "WHERE\n" +
                "	roitid = ?";
        int i = jdbcTemplate.update(sql, map.get("roid"),roitid );
        return i;
    }

    /**
     * 删除购物车中选择要生成订单的记录
     * @return
     */
    public int delordercar(String roitid){
        String sql="DELETE from resorderitemtemp where roitid=?";
        int i = jdbcTemplate.update(sql, roitid);
        return i;
    }




}
