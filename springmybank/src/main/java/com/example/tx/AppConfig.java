package com.example.tx;

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
@ComponentScan(basePackages = {"com.example"})
@EnableTransactionManagement
public class AppConfig {
    //手动创建数据连接池的数据源



    @Bean
    public TransactionManager DataSourceTransactionManager(DataSource ds){
        return new DataSourceTransactionManager(ds);
    }

}
