package com.king.springredis.testredis.string;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-08 18:43
 */
@Repository
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    //    删除key
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     * @return
     */
    public boolean deletes(String... keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 指定key的失效时间
     *
     * @param key  key
     * @param time 多少分钟
     * @return
     */
    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.MINUTES);
    }

    /**
     * 指定key的失效时间
     *
     * @param key  key
     * @param time 时间长度
     * @param unit 时间单位
     * @return
     */
    public boolean expire(String key, long time, TimeUnit unit) {
        return redisTemplate.expire(key, time, unit);
    }

    /**
     * 根据key获取过期时间
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 建一个string的键值对
     *
     * @param key
     * @param s
     */
    public void insert(String key, String s) {
        redisTemplate.boundValueOps(key).set(s);
    }

    /**
     * 建一个string的键值对
     *
     * @param key
     * @param num
     */
    public void insert(String key, Object num) {
        redisTemplate.boundValueOps(key).set(num, 1);
    }

    /**
     * 建一个string的键值对
     *
     * @param key
     * @param s
     */
    public void insert1(String key, String s) {
        redisTemplate.boundValueOps(key).set(s);
    }

    /**
     * 建一个string的键值对
     *
     * @param key
     * @param s
     */
    public void insert1(String key, String s, long time, TimeUnit unit) {
        redisTemplate.boundValueOps(key).set(s, time, unit);
    }

    /**
     * 通过BoundValueOperations设置值
     *
     * @param key
     * @param s
     */
    public void insert2(String key, String s) {
        //2、通过BoundValueOperations设置值
        BoundValueOperations stringKey = redisTemplate.boundValueOps(key);
        stringKey.set(s);

    }

    /**
     * 通过BoundValueOperations设置值
     *
     * @param key
     * @param s
     */
    public void insert2(String key, String s, long time, TimeUnit unit) {
        BoundValueOperations stringKey = redisTemplate.boundValueOps(key);
        stringKey.set("StringValue", 1, TimeUnit.MINUTES);

//        //3、通过ValueOperations设置值
//        ValueOperations ops = redisTemplate.opsForValue();
//        ops.set("StringKey", "StringVaule");
//        ops.set("StringValue","StringVaule",1, TimeUnit.MINUTES);
    }

    /**
     * 获取key 的值
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return (String) redisTemplate.boundValueOps(key).get();
    }

    /**
     * key 存在且是数字值
     * 负数为减法 正数为加
     *
     * @param key
     * @return
     */
    public Long num(String key, long l) {
        return redisTemplate.boundValueOps(key).increment(l);
    }

    /**
     * key 存在且是数字值
     * 负数为减法 正数为加
     *
     * @param key
     * @return
     */
    public Double num(String key, double d) {
        return redisTemplate.boundValueOps(key).increment(d);
    }
}
