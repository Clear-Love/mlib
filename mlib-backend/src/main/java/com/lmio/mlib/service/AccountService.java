package com.lmio.mlib.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmio.mlib.entity.Account;
import com.lmio.mlib.entity.AccountWithRole;

public interface AccountService extends IService<Account> {
    Account findAccountByNameOrEmail(String text);

    AccountWithRole findAccountWithRole(String text);
}
