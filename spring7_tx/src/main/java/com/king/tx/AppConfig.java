package com.king.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-14 19:23
 */
@Configuration
@ComponentScan(basePackages = {"com.king"})
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
//        DataSource ds = new ComboPooledDataSource();
//        ((ComboPooledDataSource) ds).setDriverClass("com.mysql.cj.jdbc.Driver");
//        ((ComboPooledDataSource) ds).setJdbcUrl("jdbc:mysql://localhost:3306/mybatis?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8");
//        ((ComboPooledDataSource) ds).setUser("root");
//        ((ComboPooledDataSource) ds).setPassword("aaaa");
//        return (ComboPooledDataSource) ds;
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/mybatis?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8");
        ds.setUser("root");
        ds.setPassword("aaaa");
        return ds;
    }
}
