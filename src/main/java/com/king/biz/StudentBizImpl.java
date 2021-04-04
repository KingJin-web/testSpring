package com.king.biz;


import com.king.dao.StudentDao;
import com.king.dao.StudentMybatisImpl;

import java.util.Random;

/**
 * @program: maven_1
 * @description:
 * @author: King
 * @create: 2021-04-04 14:26
 */
public class StudentBizImpl {
    private StudentDao studentDao ;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {
        studentDao = new StudentMybatisImpl();
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int add() {
        System.out.println("====业务层=====");
        System.out.println("业务操作");
        System.out.println("====业务结束=====");
        return studentDao.add("张三");

    }

    public void update(String name) {
        System.out.println("====业务层=====");
        System.out.println("业务操作");

        studentDao.update(name);
        System.out.println("====业务结束=====");
    }

}
