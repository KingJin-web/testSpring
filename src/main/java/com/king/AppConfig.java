package com.king;

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

}
