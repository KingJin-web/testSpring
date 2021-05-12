package com.king.springredis.test;

import com.king.springredis.testredis.hash.RedisUtilHash;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilHashTest {
    @Autowired
    private RedisUtilHash redisUtilHash;
    private HashMap<String, String> hashMap = new HashMap<>();

    @Before
    public void before() {
        hashMap.put("1","蔡徐坤");
        hashMap.put("2","王一博");
        hashMap.put("3","肖战");
    }

    @Test
    public void insert() {
        redisUtilHash.insert();
    }

    @Test
    public void testInsert() {
        System.out.println(hashMap);
        redisUtilHash.insert("yyds",hashMap);
    }

    @Test
    public void getLKey() {
    }

    @Test
    public void getValue() {
    }

    @Test
    public void testGetValue() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void getMap() {
        System.out.println( redisUtilHash.getMap("yyds"));
    }

    @Test
    public void hasKey() {
    }
}