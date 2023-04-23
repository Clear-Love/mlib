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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lmio.mlib.security.handler.AuthFailureHandler;
import com.lmio.mlib.security.handler.AuthSuccessHandler;
import com.lmio.mlib.security.handler.MyLogoutSuccessHandler;
import com.lmio.mlib.service.AuthorizeService;

import jakarta.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    AuthorizeService authorizeService;

    @Resource
    private AuthSuccessHandler authSuccessHandler;

    @Resource
    private AuthFailureHandler authFailureHandler;

    @Resource
    private AuthEntryPoint authEntryPoint;

    @Resource
    private MyLogoutSuccessHandler logoutSuccessHandler;

    @Resource
    private RedisPersistentRe redisPersistentRe;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
        http
            .authorizeHttpRequests()
            .requestMatchers("api/auth/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenRepository(redisPersistentRe)
                .and().csrf().disable()
                .cors()
                .configurationSource(this.corsConfigurationSource())
                .and()
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

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOriginPattern("*");
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        cors.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }
}
