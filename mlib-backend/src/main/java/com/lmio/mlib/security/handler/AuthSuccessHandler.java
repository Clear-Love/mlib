/*
 * @Author: lmio
 * @Date: 2023-04-06 21:49:39
 * @LastEditTime: 2023-04-11 21:16:58
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/security/AuthSuccessHandler.java
 * @Description: 登陆成功处理逻辑
 */
package com.lmio.mlib.security.handler;

import java.io.IOException;

import com.lmio.mlib.entity.User;
import com.lmio.mlib.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lmio.mlib.entity.RestBean;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    UserMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        String username = user.getUsername();
        User userinfo =  mapper.findUserByNameOrEmail(username);
        if (request != null) {
            request.getSession().setAttribute("user-info", userinfo);
        }
        response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功！", userinfo)));
    }}