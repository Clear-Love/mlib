/*
 * @Author: lmio
 * @Date: 2023-04-23 16:50:08
 * @LastEditTime: 2023-04-23 17:05:28
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/controller/AuthorizeController.java
 * @Description: 
 */

package com.lmio.mlib.controller;

import org.hibernate.validator.constraints.Length;
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
    private static final String USERNAME_REGEX = "^[A-Za-z0-9]+$";

    @Resource
    AuthorizeService service;

    /**
     * @description: 验证邮件地址
     * @return {*}
     */    
    @PostMapping("valid-email")
    public RestBean<String> validateEmail(@Pattern(regexp = EMAIL_REGEX)
                                              @RequestParam("email") String email) {
        String message = service.sendValidateEmail(email);
        if (message == null){
            return RestBean.success("验证邮件已发送");
        }else {
            return RestBean.failure(message);
        }
    }

    @PostMapping("/register")
    public RestBean<String> registerUser(@Pattern(regexp = USERNAME_REGEX) @Length(min = 3, max = 10) @RequestParam("username") String username,
                                         @Length(min = 6, max = 16) @RequestParam("password") String password,
                                         @Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                         @Length(min = 6, max = 6) @RequestParam("code") String code) {
        String message = service.validateAndRegister(username, password, email, code);
        if(message == null) {
            return RestBean.success("注册成功");
        }else {
            return RestBean.failure(400, message);
        }
    }
    
}