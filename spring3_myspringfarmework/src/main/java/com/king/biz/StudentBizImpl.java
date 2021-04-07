package com.king.biz;


import com.king.dao.StudentDao;
import com.king.springframework.stereotype.MyAutowired;
import com.king.springframework.stereotype.MyQualifier;
import com.king.springframework.stereotype.MyService;

/**
 * @program: maven_1
 * @description:
 * @author: King
 * @create: 2021-04-04 14:26
 */
@MyService//给spring的类托管
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

    @MyAutowired
    @MyQualifier("studentDaoImpl")
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
}