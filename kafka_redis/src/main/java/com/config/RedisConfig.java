package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author:
 * @date: created in 20:27 2020/8/12
 * @version:
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //关联
        redisTemplate.setConnectionFactory(factory);
        //设置key的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}