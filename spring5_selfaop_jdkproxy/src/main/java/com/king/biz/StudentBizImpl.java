package com.king.biz;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-10 19:36
 */
public class StudentBizImpl implements StudentBiz {
    @Override
    public void add(String name) {
        System.out.println("添加学生" + name);
    }

    @Override
    public void update(String name) {
        System.out.println("修改学生" + name);
    }

    /**
     * 查找
     * @param name
     */
    @Override
    public void find(String name) {
        System.out.println("查找学生" + name);

    }
}
