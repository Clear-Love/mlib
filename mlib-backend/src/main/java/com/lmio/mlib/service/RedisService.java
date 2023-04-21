/*
 * @Author: lmio
 * @Date: 2023-04-21 17:24:37
 * @LastEditTime: 2023-04-21 17:37:53
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/service/RedisService.java
 * @Description: 
 */
package com.lmio.mlib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
