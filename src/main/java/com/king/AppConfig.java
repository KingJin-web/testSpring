package com.king;

import com.king.biz.StudentBizImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-04 14:45
 */
@Configuration //表示这是一个配置类
@ComponentScan(basePackages = "com.king")
public class AppConfig {

    // bean 容器是Map<String,Object>
    //studentBizImpl 是
    @Bean
    public StudentBizImpl studentBizImpl(){

        return new StudentBizImpl();
    }


    public static void main(String[] args) {

    }
}
