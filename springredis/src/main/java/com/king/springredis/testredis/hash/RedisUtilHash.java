package com.king.springredis.testredis.hash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: testSpring
 * @description: Hash类型相关操作
 * @author: King
 * @create: 2021-05-12 19:50
 */
@Repository
public class RedisUtilHash {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }
    public void insert() {
        //1、通过redisTemplate设置值
        redisTemplate.boundHashOps("HashKey1").put("SmallKey", "HashVaue");

//2、通过BoundValueOperations设置值
        BoundHashOperations hashKey = redisTemplate.boundHashOps("HashKey2");
        hashKey.put("SmallKey", "HashVaue");

//3、通过ValueOperations设置值
        HashOperations hashOps = redisTemplate.opsForHash();
        hashOps.put("HashKey3", "SmallKey", "HashVaue");

    }

    /**
     * 插入一个 map
     *
     * @param key
     * @param map
     */
    public void insert(String key, HashMap map) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        redisTemplate.boundHashOps("HashKey").putAll(hashMap );
        redisTemplate.boundHashOps(key).putAll(map);
    }

    /**
     * 提取所有的小key
     * <p>
     * 2、通过BoundValueOperations获取值
     * BoundHashOperations hashKey = redisTemplate.boundHashOps("HashKey");
     * Set keys2 = hashKey.keys();
     * 3、通过ValueOperations获取值
     * HashOperations hashOps = redisTemplate.opsForHash();
     * Set keys3 = hashOps.keys("HashKey");
     *
     * @param key
     */
    public Set<String> getLKey(String key) {
        return redisTemplate.boundHashOps(key).keys();
    }

    /**
     * 提取所有的value值
     * <p>
     * 2、通过BoundValueOperations获取值
     * BoundHashOperations hashKey = redisTemplate.boundHashOps("HashKey");
     * List values2 = hashKey.values();
     * <p>
     * 3、通过ValueOperations获取值
     * HashOperations hashOps = redisTemplate.opsForHash();
     * List values3 = hashOps.values("HashKey");
     *
     * @param key
     * @return
     */
    public List<Object> getValue(String key) {
        return redisTemplate.boundHashOps(key).values();
    }

    /**
     * 根据key提取value值
     * <p>
     * 2、通过BoundValueOperations获取值
     * BoundHashOperations hashKey = redisTemplate.boundHashOps("HashKey");
     * String value2 = (String) hashKey.get("SmallKey");
     * <p>
     * 3、通过ValueOperations获取值
     * HashOperations hashOps = redisTemplate.opsForHash();
     * String value3 = (String) hashOps.get("HashKey", "SmallKey");
     *
     * @param key
     * @param smallKey
     * @return
     */
    public String getValue(String key, String smallKey) {
        //1、通过redisTemplate获取
        return (String) redisTemplate.boundHashOps(key).get(smallKey);
    }

    /**
     * 删除小key
     *
     * @param key
     * @param smallKey
     * @return
     */
    public Long delete(String key, String smallKey) {
        //删除小key
        return redisTemplate.boundHashOps(key).delete(smallKey);
    }

    /**
     * 删除大key
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 获取所有的键值对集合
     * <p>
     * 2、通过BoundValueOperations获取值
     * BoundHashOperations hashKey = redisTemplate.boundHashOps("HashKey");
     * Map entries1 = hashKey.entries();
     * <p>
     * 3、通过ValueOperations获取值
     * HashOperations hashOps = redisTemplate.opsForHash();
     * Map entries2 = hashOps.entries("HashKey");
     *
     * @param key
     * @return
     */
    public Map getMap(String key) {
        return redisTemplate.boundHashOps("HashKey").entries();
    }

    public boolean hasKey(String key, String smallKey) {
        return redisTemplate.boundHashOps(key).hasKey(smallKey);
    }
}
