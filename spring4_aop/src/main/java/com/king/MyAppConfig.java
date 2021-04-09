package com.king;

import com.king.bean.HelloWorld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 11:47
 */
@Configuration
@ComponentScan(basePackages = {"com.king"})
public class MyAppConfig {

    @Bean
    public HelloWorld hw() {
        return new HelloWorld();
    }
}
