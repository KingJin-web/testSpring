package com.king.biz;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-04 15:09
 */
@Component
public class HelloWorld {
    public HelloWorld() {
        System.out.println("无参构造");
    }

    public void Hello() {
        System.out.println("hello world");

    }
}
