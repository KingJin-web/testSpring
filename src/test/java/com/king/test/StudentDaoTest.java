package com.king.test;

import com.king.AppConfig;
import com.king.tx.dao.StudentDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentDaoTest {
    //    private StudentDao studentDao = null;
    ApplicationContext ac = null;

    @Before
    public void setUp() throws Exception {
        //studentDao = new StudentMybatisImpl();
//        studentDao = new StudentJpaImpl();
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        StudentDao studentDao = (StudentDao) ac.getBean("studentJpaImpl");
        studentDao.add("张三");

        StudentDao studentDao2 = (StudentDao) ac.getBean("studentMybatisImpl");
        studentDao2.add("张三");
    }

    @Test
    public void update() {
        StudentDao studentDao = (StudentDao) ac.getBean("studentJpaImpl");
        studentDao.update("张三");

        StudentDao studentDao2 = (StudentDao) ac.getBean("studentMybatisImpl");
        studentDao2.add("张三");
    }
}

