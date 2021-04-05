package com.king.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;
import java.util.Random;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-04 14:49
 */
@Repository
@Component
public class StudentJpaImpl implements StudentDao {
    @Override
    public int add(String name) {
        System.out.println("jpa=========");
        System.out.println("添加学生" + name);
        return new Random().nextInt();

    }

    @Override
    public void update(String name) {
        System.out.println("jpa=========");
        System.out.println("更新学生" + name);
    }
}
