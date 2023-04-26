package com.lmio.mlib.config;

import com.lmio.mlib.interceptor.AuthorizeInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午9:25 2023/4/26
 * @Modified By:lmio
 */

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Resource
    AuthorizeInterceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(interceptor)
                .addPathPatterns("/api/user/me");
    }
}
