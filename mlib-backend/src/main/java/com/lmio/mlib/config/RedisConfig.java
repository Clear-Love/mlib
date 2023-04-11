package com.lmio.mlib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig
{
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置key序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value序列化器
        redisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer<>(Object.class));
        // 设置hash key序列化器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 设置hash value序列化器
        redisTemplate.setHashValueSerializer(new FastJson2JsonRedisSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}

