package com.king.biz;


import com.king.dao.StudentDao;
import com.king.dao.StudentMybatisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Random;

/**
 * @program: maven_1
 * @description:
 * @author: King
 * @create: 2021-04-04 14:26
 */
//@Service
public class StudentBizImpl {
    private StudentDao studentDao;


    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {
        studentDao = new StudentMybatisImpl();
    }

    @Inject
    //javax 中的依赖注入 如有多个对象()

    @Autowired
    @Qualifier("studentJpaImpl")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int add(String name) {
        System.out.println("====业务层=====");
        System.out.println("业务操作");

        int r = studentDao.add(name);
        System.out.println("====业务结束=====");
        return r;
    }

    public void update(String name) {
        System.out.println("====业务层=====");
        System.out.println("业务操作");

        studentDao.update(name);
        System.out.println("====业务结束=====");
    }

}
