package com.king.bean;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 11:45
 */
@Component
public class HelloWorld {

    @PostConstruct
    public void setup (){
        System.out.println("MyPostConstruct");
    }

    @PreDestroy
    public void destroy(){
        System.out.println(" @MyPreDestroy");
    }

    public HelloWorld() {
        System.out.println("构造");
    }

    public void show(){
        System.out.println("Hello world");
        
    }

}
