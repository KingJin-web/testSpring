package com.yc;

import com.yc.biz.FoodInfoBiz;
import com.yc.dao.ResFoodImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class OrderspringbootcaseApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    FoodInfoBiz foodInfoBiz;
    @Autowired
    ResFoodImp resFoodImp;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    void contextLoads2() throws SQLException {
        List<Map<String, Object>> maps = foodInfoBiz.getfoodspageinfo("2");
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
        Integer integer = resFoodImp.querycountfoods();
        System.out.println(integer);

        System.out.println(foodInfoBiz.getallcount());
    }

}
