package com.king;

import com.king.bean.HelloWorld;
import com.king.springframework.context.MyAnnotationConfigApplicationContext;
import com.king.springframework.context.MyApplicationContext;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 11:30
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        MyApplicationContext ac = new MyAnnotationConfigApplicationContext(MyAppConfig.class);
        HelloWorld hw = (HelloWorld) ac.getBean("hw");
        hw.show();
    }
}
