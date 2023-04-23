/*
 * @Author: lmio
 * @Date: 2023-04-23 16:50:08
 * @LastEditTime: 2023-04-23 17:05:28
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/controller/AuthorizeController.java
 * @Description: 
 */

package com.lmio.mlib.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmio.mlib.entity.RestBean;
import com.lmio.mlib.service.AuthorizeService;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;

/**
 * AuthorizeController
*/
@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,}$";

    @Resource
    AuthorizeService service;

    /**
     * @description: 验证邮件地址
     * @return {*}
     */    
    @PostMapping("valid-email")
    public RestBean<String> validateEmail(@Pattern(regexp = EMAIL_REGEX)
    @RequestParam("email") String email) {
        if (service.sendValidateEmail(email)){
            return RestBean.success("验证邮件已发送");
        }else {
            return RestBean.failure("邮件发送失败");
        }
    }
    
}