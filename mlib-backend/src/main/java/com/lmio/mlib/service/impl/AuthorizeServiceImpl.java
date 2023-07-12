/*
 * @Author: lmio
 * @Date: 2023-04-06 20:54:32
 * @LastEditTime: 2023-04-23 17:21:32
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/service/impl/AuthorizeServiceimpl.java
 * @Description:
 */
package com.lmio.mlib.service.impl;

import com.lmio.mlib.entity.Account;
import com.lmio.mlib.entity.AccountWithRole;
import com.lmio.mlib.entity.User;
import com.lmio.mlib.service.AccountService;
import com.lmio.mlib.service.AuthorizeService;
import com.lmio.mlib.service.UserSerivce;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Configuration
@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Resource
    private HttpServletRequest request;

    @Resource
    private UserSerivce userSerivce;

    @Resource
    private AccountService accountService;

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private MailSender mailSender;

    @Resource
    private StringRedisTemplate template;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        AccountWithRole account = accountService.findAccountWithRole(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        User userinfo = userSerivce.findUserByNameOrEmail(username);

        HttpSession session = request.getSession();
        session.setAttribute("user-info", userinfo);

        // 得到用户角色
        String role = account.getRole();
        // 角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

        return org.springframework.security.core.userdetails.User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .authorities(authorities)
                .build();
    }

    /**
     * @return {*}
     * @description: 1.生成验证码
     * 2.把邮箱和对应的验证码直接放到Redis（过期时间3分钟）
     * 3.发送验证码到指定邮箱
     * 4.发送失败，把Redis里面刚刚插入的删除
     * 5.用户注册时，再从Redis里面取出对应键值对，然后看验证码是否一致
     */
    @Transactional
    @Override
    public String sendValidateEmail(String email, boolean hasAccount) {
        String key = "email" + email + hasAccount;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            Long expire = template.getExpire(key, TimeUnit.SECONDS);
            expire = expire == null ? 0 : expire;
            if (expire > 120) {
                return "请求频繁，请稍后再试";
            }
        }
        Account account = accountService.findAccountByNameOrEmail(email);
        if (account != null && !hasAccount) {
            return "此邮箱已经被注册";
        }
        if (account == null && hasAccount) {
            return "没有此邮箱的账户";
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
            return null;
        } catch (MailException e) {
            e.printStackTrace();
            return "邮件发送失败，请检查邮件地址是否有效";
        }

    }

    @Override
    public String validateUsername(String username) {
        if (accountService.findAccountByNameOrEmail(username) != null) {
            return "该用户已经存在";
        }
        return null;
    }

    @Override
    public String validateAndRegister(String username, String password, String email, String code) {
        String message = validateUsername(username);
        if (message != null) {
            return message;
        }
        String key = "email" + email + false;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            String s = template.opsForValue().get(key);
            if (s == null) {
                return "验证码失效，请重新请求";
            }
            if (s.equals(code)) {
                password = passwordEncoder.encode(password);
                template.delete(key);
                Account account = new AccountWithRole("user", username, password, email);
                if (accountService.save(account)) {
                    return null;
                } else {
                    return "内部错误";
                }

            } else {
                return "验证码错误，请重新检查后再提交";
            }
        }
        return "请获取验证码后再试";
    }

    @Override
    public String validateOnly(String email, String code) {
        String key = "email" + email + true;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            String s = template.opsForValue().get(key);
            if (s == null) {
                return "验证码失效，请重新请求";
            }
            if (s.equals(code)) {
                template.delete(key);
                return null;
            } else {
                return "验证码错误，请重新检查后再提交";
            }
        }
        return "请获取验证码后再试";
    }

    @Override
    public boolean resetPassword(String email, String password) {
        password = passwordEncoder.encode(password);
        Account account = accountService.findAccountByNameOrEmail(email);
        account.setPassword(password);
        return accountService.updateById(account);
    }
}