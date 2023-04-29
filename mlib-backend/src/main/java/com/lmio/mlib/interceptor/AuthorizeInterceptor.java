package com.lmio.mlib.interceptor;

import com.lmio.mlib.entity.UserConfig;
import com.lmio.mlib.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    UserMapper mapper;

    @Override
    public boolean preHandle(@Nullable HttpServletRequest request,@Nullable HttpServletResponse response,@Nullable Object handler) throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        UserConfig userConfig =  mapper.findUserConfigByName(username);
        System.out.println(userConfig);
        if (request != null) {
            request.getSession().setAttribute("user-info", userConfig);
        }else {
            return false;
        }
        return true;
    }
}
