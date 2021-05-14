package com.example.springmongodb.test;

import com.example.springmongodb.bean.User;
import com.example.springmongodb.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

;

@SpringBootTest
@RunWith(SpringRunner.class)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class userDaoTest {
    @Autowired
    private UserDao userDao ;

    @Test
    public void testFindByName() {
        System.out.println(userDao.findByName("吴国峰"));
    }

    @Test
    public void testUpdate(){
        userDao.update("age",1,100);
    }

    @Test
    public void testInsert(){
        userDao.insert(new User(1,20,"吴国峰"));
    }

    @Test
    public void testDelete(){
        userDao.delete(1);
    }
}

