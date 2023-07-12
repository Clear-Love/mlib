package com.lmio.mlib.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmio.mlib.entity.User;
import com.lmio.mlib.mapper.UserMapper;
import com.lmio.mlib.service.UserSerivce;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserSerivce {
    @Resource
    UserMapper mapper;

    @Override
    public User findUserByNameOrEmail(String text) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username", text)
                .or()
                .eq("email", text);
        return mapper.selectOne(queryWrapper);
    }
}
