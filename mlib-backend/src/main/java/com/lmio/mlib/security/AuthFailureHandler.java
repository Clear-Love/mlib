/*
 * @Author: lmio
 * @Date: 2023-04-11 20:43:42
 * @LastEditTime: 2023-04-11 21:17:45
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/security/AuthFailureHandler.java
 * @Description: 
 */
/*
 * @Author: lmio
 * @Date: 2023-04-06 21:49:39
 * @LastEditTime: 2023-04-11 20:43:34
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/security/AuthSuccessHandler.java
 * @Description: 登陆成功处理逻辑
 */
package com.lmio.mlib.security;

import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lmio.mlib.entity.RestBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure("账号或密码错误！")));
    }
    
}