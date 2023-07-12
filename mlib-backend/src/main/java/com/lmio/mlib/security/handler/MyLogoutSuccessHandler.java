/*
 * @Author: lmio
 * @Date: 2023-04-13 17:08:56
 * @LastEditTime: 2023-04-13 17:14:21
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/security/MyLogoutSuccessHandler.java
 * @Description:
 */
package com.lmio.mlib.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.lmio.mlib.Bean.RestBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.success("退出登录！")));
    }

}