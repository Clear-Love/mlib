/*
 * @Author: lmio
 * @Date: 2023-04-06 21:49:39
 * @LastEditTime: 2023-04-06 21:56:29
 * @FilePath: /mlib/src/main/java/com/lmio/mlib/security/AuthenticationSucessHandler.java
 * @Description: 登陆成功处理逻辑
 */
package com.lmio.mlib.security;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationSucessHandler implements AuthenticationSuccessHandler {

    // private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    //
    // @Autowired
    // private ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        //response.setContentType("application/json;charset=utf-8");
        //response.getWriter().write(mapper.writeValueAsString(authentication));
        // SavedRequest savedRequest = requestCache.getRequest(request, response);
        // System.out.println(savedRequest.getRedirectUrl());
        // redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        // 重定向到：/hello
        redirectStrategy.sendRedirect(request, response, "/hello");
    }
}