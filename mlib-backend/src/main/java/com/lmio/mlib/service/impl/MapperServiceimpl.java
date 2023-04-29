package com.lmio.mlib.service.impl;

import com.lmio.mlib.mapper.UserMapper;
import com.lmio.mlib.service.MapperService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: lmio
 * @Description: TODO 一些mapper的事务
 * @Date: 下午7:11 2023/4/29
 * @Modified By:lmio
 */

@Transactional
@Service
public class MapperServiceimpl implements MapperService {

    @Resource
    UserMapper userMapper;

    /**
     * @deprecated : 将注册流程注册为一个事务，防止中途失败
     * @return : string
     */

    @Override
    public String register(String username, String password, String email) {
        // 创建用户账户 创建用户配置
        if (userMapper.createAccount(username, password, email) > 0 &&
                userMapper.createUserConfig(username) > 0) {
            return null;
        }
        return "内部错误";
    }
}
