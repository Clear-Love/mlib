/*
 * @Author: lmio
 * @Date: 2023-04-06 20:54:32
 * @LastEditTime: 2023-04-23 17:21:32
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/service/impl/AuthorizeServiceimpl.java
 * @Description: 
 */
package com.lmio.mlib.service.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.lmio.mlib.entity.Account;
import com.lmio.mlib.mapper.UserMapper;
import com.lmio.mlib.service.AuthorizeService;

import jakarta.annotation.Resource;

@Configuration
public class AuthorizeServiceimpl implements AuthorizeService {

    @Resource
    UserMapper mapper;

    @Value("${spring.mail.username}")
    String from;

    @Resource
    MailSender mailSender;

    @Resource
    StringRedisTemplate template;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        Account account = mapper.findAccountByNameOrEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        

        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("admin")
                .build();
    }

    /**
     * @description:
     * 1.生成验证码
     * 2.把邮箱和对应的验证码直接放到Redis（过期时间3分钟）
     * 3.发送验证码到指定邮箱
     * 4.发送失败，把Redis里面刚刚插入的删除
     * 5.用户注册时，再从Redis里面取出对应键值对，然后看验证码是否一致
     * @return {*}
     */    
    @Override
    public boolean sendValidateEmail(String email) {
        String key = "email" + email;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            Long expire = template.getExpire(key, TimeUnit.SECONDS);
            expire = expire == null ? 0: expire;
            if (expire > 120) {
                return false;
            }
        }
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("验证邮件");
        message.setText("验证码是：" + code);
        try {
            mailSender.send(message);
            // 存入redis, 过期时间为3分钟
            template.opsForValue().set(key, String.valueOf(code), 3, TimeUnit.MINUTES);
            return true;
        }catch (MailException e) {
            e.printStackTrace();
            return false;
        }
    }
}