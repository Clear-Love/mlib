/*
 * @Author: lmio
 * @Date: 2023-04-06 20:54:32
 * @LastEditTime: 2023-04-12 19:27:33
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/service/AuthorizeService.java
 * @Description: 
 */
package com.lmio.mlib.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.lmio.mlib.entity.Account;
import com.lmio.mlib.mapper.UserMapper;

import jakarta.annotation.Resource;

@Configuration
public class AuthorizeService implements UserDetailsService {

    @Resource
    UserMapper mapper;

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
}