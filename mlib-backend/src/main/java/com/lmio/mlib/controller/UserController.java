package com.lmio.mlib.controller;

import com.lmio.mlib.entity.Account;
import com.lmio.mlib.entity.RestBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午4:07 2023/4/26
 * @Modified By:lmio
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/info")
    public RestBean<Account> getUserConfig(@SessionAttribute("user-info") Account account) {
        return RestBean.success(account);
    }
}
