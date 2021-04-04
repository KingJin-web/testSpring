package com.king.dao;

import java.util.Random;

/**
 * @program: maven_1
 * @description:
 * @author: King
 * @create: 2021-04-04 14:28
 */
public class StudentMybatisImpl implements StudentDao {
    @Override
    public int add(String name) {
        System.out.println("mybatis=========");
        System.out.println("添加学生" +name);

        return new Random().nextInt();
    }

    @Override
    public void update(String name) {
        System.out.println("mybatis=========");
        System.out.println("更新学生" +name);
    }


}
