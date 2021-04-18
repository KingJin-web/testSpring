package com.king.test;

import com.king.MyAppConfig;
import com.king.biz.StudentBizImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyAppConfig.class)
public class StudentBizImplTest {

    //@Resource(name = "studentBizImpl")
    @Autowired
    private StudentBizImpl studentBiz;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void add() {
        studentBiz.add("张三");
    }

    @Test
    public void update() {
        studentBiz.update("张三");
    }

    @Test
    public void find() {
        studentBiz.find("张三");
    }
}