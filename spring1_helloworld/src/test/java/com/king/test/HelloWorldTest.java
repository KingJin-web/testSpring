package com.king.test;

import com.king.AppConfig;
import com.king.biz.HelloWorld;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class HelloWorldTest {
    @Autowired
    private ApplicationContext ac;
    @Autowired
    HelloWorld hw;
    @Autowired
    HelloWorld hw2;

    //ApplicationContext 基于注解的配置容器类

    @Before
    public void setUp() throws Exception {
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //读取AppConfig -> basePackages -> "yc.com" ->得到要扫描的路径
        //检查这些包中有没有@Component注解 有则实例化
        //存到Map<string,Object> 中
        System.out.println(ac.toString());
    }

    @Test
    public void hello() {
//        HelloWorld hw = (HelloWorld) ac.getBean("helloWorld");
        hw.Hello();
//        HelloWorld hw2 = (HelloWorld) ac.getBean("helloWorld");
        hw2.Hello();
        //spring 容器是单例模型
    }
}