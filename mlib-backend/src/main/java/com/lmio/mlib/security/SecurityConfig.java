package com.lmio.mlib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lmio.mlib.service.AuthorizeService;

import jakarta.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    AuthorizeService authorizeService;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
        http
            .authorizeHttpRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .and().csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(authEntryPoint);
        http.headers().frameOptions().sameOrigin();
 
        return http.build();
    }

    // 用于密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
        return security
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authorizeService)
                .and()
                .build();
    }
}
