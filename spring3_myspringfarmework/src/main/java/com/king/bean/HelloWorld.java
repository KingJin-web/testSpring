package com.king.bean;

import com.king.springframework.stereotype.MyComponent;
import com.king.springframework.stereotype.MyPostConstruct;
import com.king.springframework.stereotype.MyPreDestroy;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 11:45
 */
@MyComponent
public class HelloWorld {

    @MyPostConstruct
    public void setup (){
        System.out.println("MyPostConstruct");
    }

    @MyPreDestroy
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
