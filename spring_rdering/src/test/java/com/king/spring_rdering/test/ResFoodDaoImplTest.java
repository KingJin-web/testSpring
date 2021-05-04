package com.king.spring_rdering.test;

import com.king.spring_rdering.dao.ResFoodDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ResFoodDaoImplTest {
    @Autowired
    private ResFoodDao resFoodDao;

    @Test
    public void findAll() {
       // System.out.println(resFoodDao.findAll());
        System.out.println(resFoodDao.findAll(1,5));
        System.out.println(resFoodDao.findAll(2,5)); ;
        System.out.println(resFoodDao.findAll(3,5));;
        System.out.println(resFoodDao.findAll(4,5));;
    }

    @Test
    public void findByName() {
        System.out.println(resFoodDao.findByName("素炒莴笋丝"));
    }

    @Test
    public void update() {
    }
}