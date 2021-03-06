package com.king.springredis.test;

import com.king.springredis.testredis.string.RedisUtil;
import com.king.springredis.util.TimeHelp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void delete() {
        redisUtil.delete("Hello");
    }

    @Test
    public void deletes() {
    }

    @Test
    public void expire() {
        redisUtil.expire("Hello", 1, TimeUnit.DAYS);
    }

    @Test
    public void getExpire() {
        System.out.println(redisUtil.getExpire("Hello"));
    }

    @Test
    public void hasKey() {
        System.out.println(redisUtil.hasKey("Hello"));
    }


    @Test
    public void insert() {
        redisUtil.insert("nowTime", TimeHelp.nowTime());
        redisUtil.insert("num1", "大家迫a切");
        redisUtil.insert("num2", "123a");
        //redisUtil.num("2",15);
    }

    @Test
    public void getString() {
        System.out.println(redisUtil.getString("Hello"));
    }

    @Test
    public void get() {
//        int a = Integer.parseInt(String.valueOf(redisUtil.getObject("Hello1")));
//        System.out.println(a);
//        int b = redisUtil.getInt("Hello1");
//        System.out.println(b);
        redisUtil.insert("test1", "123");
        int a = Integer.parseInt(String.valueOf(redisUtil.getObject("test1")));
        System.out.println(a);
        String aa = "-123";
        a = Math.toIntExact(redisUtil.num("test1", Integer.parseInt(aa)));
        System.out.println(a);
        a = Integer.parseInt(String.valueOf(redisUtil.getObject("test1")));
        System.out.println(a);
    }

    @Test
    public void add() {
        //redisTemplate.boundValueOps("StringKey").increment(3L);
        System.out.println(redisUtil.getString("Hello1"));
        System.out.println(redisUtil.num("Hello1", 15));
        System.out.println(redisUtil.num("Hello1", 15));
        System.out.println(redisUtil.num("Hello1", -13));
    }
}