package com.lmio.mlib.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmio.mlib.entity.User;

public interface UserSerivce extends IService<User> {
    User findUserByNameOrEmail(String text);
}
