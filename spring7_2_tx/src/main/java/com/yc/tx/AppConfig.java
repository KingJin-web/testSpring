package com.yc.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-14 19:29
 */
@Configuration
@ComponentScan(basePackages = {"com.yc"})
@EnableTransactionManagement
public class AppConfig {
    //手动创建数据连接池的数据源

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        DataSource ds = new ComboPooledDataSource();
        ((ComboPooledDataSource) ds).setDriverClass("com.mysql.cj.jdbc.Driver");
        ((ComboPooledDataSource) ds).setJdbcUrl("jdbc:mysql://localhost:3306/mybatis?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8");
        ((ComboPooledDataSource) ds).setUser("root");
        ((ComboPooledDataSource) ds).setPassword("aaaa");
        return (ComboPooledDataSource) ds;
    }

    @Bean
    public TransactionManager DataSourceTransactionManager(DataSource ds){
        return new DataSourceTransactionManager(ds);
    }

}
