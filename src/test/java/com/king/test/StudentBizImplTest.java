package com.king.test;

import com.king.AppConfig;
import com.king.biz.StudentBizImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class StudentBizImplTest {
    ApplicationContext ac = null;
    StudentBizImpl studentBiz;
    @Before
    public void setUp() throws Exception {
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
      studentBiz = (StudentBizImpl) ac.getBean("studentBizImpl");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void setStudentDao() {
        studentBiz.add("蔡徐坤");
    }

    @Test
    public void add() {
        studentBiz.update("蔡徐坤");
    }
}