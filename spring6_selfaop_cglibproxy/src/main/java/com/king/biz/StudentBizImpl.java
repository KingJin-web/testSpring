package com.king.biz;


import org.springframework.stereotype.Service;

/**
 * @program: maven_1
 * @description:
 * @author: King
 * @create: 2021-04-04 14:26
 */
@Service//给spring的类托管
public class StudentBizImpl {

    public int add(String name) {
        System.out.println("调用了 StudentBizImpl add");

        return name.length();

    }

    public void update(String name) {
        System.out.println("调用了  StudentBizImpl update");
    }

    public String find(String name) {
        System.out.println("调用了  StudentBizImpl find");
        return name + "===";
    }
}