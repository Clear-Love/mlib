/*
 * @Author: lmio
 * @Date: 2023-04-21 16:39:42
 * @LastEditTime: 2023-04-21 17:16:38
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/security/RedisPersistentRe.java
 * @Description: redistoken仓库
 */
package com.lmio.mlib.security;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisPersistentRe implements PersistentTokenRepository {

    private final static String USERNAME_KEY = "spring:security:rememberMe:USERNAME_KEY:";
    private final static String SERIES_KEY = "spring:security:rememberMe:SERIES_KEY:";

    private final static int TOKEN_TIMEOUT = 7;
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        String series = token.getSeries();
        String key = generateKey(series, SERIES_KEY);
        String usernameKey = generateKey(token.getUsername(), USERNAME_KEY);
        //用户只要采用账户密码重新登录，那么为了安全就有必要清除之前的token信息。deleteIfPresent方法通过
        //username查找到用户对应的series，然后删除旧的token信息。
        deleteIfPresent(usernameKey);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", token.getUsername());
        hashMap.put("token", token.getTokenValue());
        hashMap.put("date", String.valueOf(token.getDate().getTime()));
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, hashMap);
        redisTemplate.expire(key, TOKEN_TIMEOUT, TimeUnit.DAYS);//设置token保存期限
        stringRedisTemplate.opsForValue().set(usernameKey, series);
        redisTemplate.expire(usernameKey, TOKEN_TIMEOUT, TimeUnit.DAYS);
    }

    /**
     * @Author: lmio
     * @Description: 根据usernameKey删除token
     * @param usernameKey：根据用户名生成的key
     * @Date: 2023/4/29
     */
    private void deleteIfPresent(String usernameKey) {
        //删除token时应该同时删除token信息，以及保存了对应的username与series对照数据。
        if (Boolean.TRUE.equals(redisTemplate.hasKey(usernameKey))) {
            String series = generateKey(stringRedisTemplate.opsForValue().get(usernameKey), SERIES_KEY);
            if (Boolean.TRUE.equals(redisTemplate.hasKey(series))) {
                redisTemplate.delete(series);
                redisTemplate.delete(usernameKey);
            }
        }
    }

    private String generateKey(String series, String prefix) {
        return prefix + series;
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String key = generateKey(series, SERIES_KEY);
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key)))
            redisTemplate.opsForHash().put(key, "token", tokenValue);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        String key = generateKey(series, SERIES_KEY);
        List<Object> hashKeys = new ArrayList<>();
        hashKeys.add("username");
        hashKeys.add("token");
        hashKeys.add("date");
        List<Object> hashValues = redisTemplate.opsForHash().multiGet(key, hashKeys);
        String username = (String) hashValues.get(0);
        String tokenValue = (String) hashValues.get(1);
        String date = (String) hashValues.get(2);
        if (null == username || null == tokenValue || null == date) {
            return null;
        }
        long timestamp = Long.parseLong(date);
        Date time = new Date(timestamp);
        return new PersistentRememberMeToken(username, series, tokenValue, time);
    }

    @Override
    public void removeUserTokens(String username) {
        //rememberMeService实现类中调用这个方法传入的参数是username，因此我们必须通过username查找到
        //对应的series，然后再通过series查找到对应的token信息再删除。
        String key = generateKey(username, USERNAME_KEY);
        deleteIfPresent(key);
    }

}