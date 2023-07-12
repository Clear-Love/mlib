package com.lmio.mlib.interceptor;

import com.lmio.mlib.entity.User;
import com.lmio.mlib.service.UserSerivce;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午4:10 2023/4/26
 * @Modified By:lmio
 */

@Component
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Resource
    UserSerivce userSerivce;

    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        String username = user.getUsername();
        User userinfo = userSerivce.findUserByNameOrEmail(username);
        if (request != null) {
            request.getSession().setAttribute("user-info", userinfo);
        } else {
            return false;
        }
        return true;
    }
}
