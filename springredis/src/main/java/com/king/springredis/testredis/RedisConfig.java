//package com.king.springredis.testredis;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * @program: testSpring
// * @description:
// * @author: King
// * @create: 2021-05-12 21:19
// */
//public class RedisConfig {
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//        //设置key的序列化器
//        template.setKeySerializer(new StringRedisSerializer());
//        //设置value的序列化器
//        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
//        System.out.println("string");
//        return template;
//    }
//}
