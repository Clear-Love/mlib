/*
 * @Author: lmio
 * @Date: 2023-04-06 21:49:39
 * @LastEditTime: 2023-04-11 21:16:58
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/security/AuthSuccessHandler.java
 * @Description: 登陆成功处理逻辑
 */
package com.lmio.mlib.security.handler;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lmio.mlib.entity.RestBean;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功！")));
    }}