package com.king.biz;


import com.king.dao.StudentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @program: maven_1
 * @description:
 * @author: King
 * @create: 2021-04-04 14:26
 */
@Service//给spring的类托管
public class StudentBizImpl {
    private StudentDao studentDao;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    @Autowired
    @Qualifier("studentJpaImpl")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void add(String name) {
        System.out.println(" -------业务层------------");
        System.out.println("用户名是否重名");
        studentDao.add(name);
        System.out.println("业务操作结束");
    }

    public void update(String name) {
        System.out.println("=====业务层=====");
        System.out.println("用户名是否重名");
        studentDao.update(name);
        System.out.println("业务层结束");
    }

    private final Random random = new Random();

    public void find(String name) {
        System.out.println("=====业务层=====");
        System.out.println("查找用户名");
        studentDao.find(name);
        try {
            Thread.sleep(random.nextInt());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("业务层结束");
    }
}