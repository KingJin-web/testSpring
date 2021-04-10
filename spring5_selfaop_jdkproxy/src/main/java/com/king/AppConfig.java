package com.king;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-10 19:19
 */
@Configuration
@ComponentScan(basePackages = {"com.king"})
@EnableAspectJAutoProxy
public class AppConfig {



}
