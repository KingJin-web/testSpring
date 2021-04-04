package com.king.test;

import com.king.dao.StudentDao;
import com.king.dao.StudentJpaImpl;
import com.king.dao.StudentMybatisImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentDaoTest {
    private StudentDao studentDao = null;

    @Before
    public void setUp() throws Exception {
        //studentDao = new StudentMybatisImpl();
        studentDao = new StudentJpaImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        studentDao.add("张三");
    }

    @Test
    public void update() {
        studentDao.update("张三");
    }
}

