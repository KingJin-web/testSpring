package com.king.springredis.javaredis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @program: testSpring
 * @description: 连接到 redis 服务
 * @author: King
 * @create: 2021-05-07 23:36
 */
public class RedisJava {
    public static void main(String[] args) {
//        //连接本地的 Redis 服务
//        Jedis jedis = new Jedis("localhost");
//        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
//        // jedis.auth("123456");
//        System.out.println("连接成功");
//        //查看服务是否运行
//        System.out.println("服务正在运行: "+jedis.ping());

        Jedis jedis = new Jedis("www.wuzhaoqi.top", 6379);
        jedis.auth("aaa");
        //System.out.println("服务正在运行: "+jedis.ping());


       // jedis.set("Hello", "Hello Redis");
        // 获取存储的数据并输出
       // System.out.println("redis 存储的字符串为: "+ jedis.get("Hello"));
        System.out.println(jedis.get("Hello"));

        //存储数据到列表中
        jedis.lpush("site-list", "Baidu");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Bing");
        // 获取存储的数据并输出

        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
        jedis.close();
    }
}
