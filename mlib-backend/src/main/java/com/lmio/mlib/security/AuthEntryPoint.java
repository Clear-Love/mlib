/*
 * @Author: lmio
 * @Date: 2023-04-11 21:06:17
 * @LastEditTime: 2023-04-11 21:17:37
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/security/AuthEntryPoint.java
 * @Description:
 */
package com.lmio.mlib.security;

import com.alibaba.fastjson.JSONObject;
import com.lmio.mlib.Bean.RestBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure("非法请求")));
    }

}