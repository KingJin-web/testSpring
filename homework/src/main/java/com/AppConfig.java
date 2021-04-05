package com;

import com.mimi.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 09:18
 */
@Configuration //表示这是一个配置类
@ComponentScan(basePackages = {"com.huwei", "com.mimi"})
public class AppConfig {
    @Bean
    public Random r() {
        return new Random();
    }

    @Bean
    public Person p(){
        return new Person();
    }
}
