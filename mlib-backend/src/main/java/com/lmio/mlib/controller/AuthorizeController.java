/*
 * @Author: lmio
 * @Date: 2023-04-23 16:50:08
 * @LastEditTime: 2023-04-23 17:05:28
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/controller/AuthorizeController.java
 * @Description:
 */

package com.lmio.mlib.controller;

import com.lmio.mlib.Bean.RestBean;
import com.lmio.mlib.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @return {*}
     * @description: 验证邮件地址
     */
    @PostMapping("valid-register-email")
    public RestBean<String> validateRegisterEmail(@Pattern(regexp = EMAIL_REGEX)
                                                  @RequestParam("email") String email) {
        String message = service.sendValidateEmail(email, false);
        if (message == null) {
            System.out.println("发送");
            return RestBean.success("验证邮件已发送");
        } else {
            return RestBean.failure(message);
        }
    }

    @PostMapping("valid-reset-email")
    public RestBean<String> validateResetEmail(@Pattern(regexp = EMAIL_REGEX)
                                               @RequestParam("email") String email) {
        String message = service.sendValidateEmail(email, true);
        if (message == null) {
            System.out.println("发送");
            return RestBean.success("验证邮件已发送");
        } else {
            return RestBean.failure(message);
        }
    }

    @PostMapping("/register")
    public RestBean<String> registerUser(@Pattern(regexp = USERNAME_REGEX) @Length(min = 3, max = 10) @RequestParam("username") String username,
                                         @Length(min = 6, max = 16) @RequestParam("password") String password,
                                         @Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                         @Length(min = 6, max = 6) @RequestParam("code") String code) {
        String message = service.validateAndRegister(username, password, email, code);
        if (message == null) {
            return RestBean.success("注册成功");
        } else {
            return RestBean.failure(400, message);
        }
    }

    @PostMapping("/start-reset")
    public RestBean<String> startReset(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                       @Length(min = 6, max = 6) @RequestParam("code") String code,
                                       HttpSession session) {
        String message = service.validateOnly(email, code);
        if (message == null) {
            session.setAttribute("reset-password", email);
            return RestBean.success("验证成功，请重置密码");
        } else {
            return RestBean.failure(400, message);
        }
    }

    @PostMapping("/do-reset")
    public RestBean<String> resetPassword(@Length(min = 6, max = 16) @RequestParam("password") String password,
                                          HttpSession session) {
        String email = (String) session.getAttribute("reset-password");
        if (email == null) {
            return RestBean.failure(401, "请先完成邮箱验证");
        } else if (service.resetPassword(email, password)) {
            session.removeAttribute("reset-password");
            return RestBean.success("重置密码成功");
        } else {
            return RestBean.failure(500, "内部错误，请联系管理员");
        }
    }
}