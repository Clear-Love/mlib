package com.lmio.mlib.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.lmio.mlib.entity.Account;
import com.lmio.mlib.entity.AccountWithRole;
import com.lmio.mlib.entity.Role;
import com.lmio.mlib.mapper.AccountMapper;
import com.lmio.mlib.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Resource
    AccountMapper mapper;

    @Override
    public Account findAccountByNameOrEmail(String text) {

        QueryWrapper<Account> queryWrapper = new QueryWrapper<Account>()
                .eq("username", text)
                .or()
                .eq("email", text);
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public AccountWithRole findAccountWithRole(String text) {
        MPJLambdaWrapper<Account> wrapper = new MPJLambdaWrapper<Account>()
                .selectAll(Account.class)
                .select(Role::getRole)
                .leftJoin(Role.class, Role::getRoleId, Account::getRoleId)
                .eq(Account::getUsername, text)
                .or()
                .eq(Account::getEmail, text);
        return mapper.selectJoinOne(AccountWithRole.class, wrapper);
    }
}
