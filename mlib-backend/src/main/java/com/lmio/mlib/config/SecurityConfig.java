package com.lmio.mlib.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.lmio.mlib.security.AuthenticationSucessHandler;

@Configuration
public class SecurityConfig {
    @Autowired
    private AuthenticationSucessHandler authenticationSucessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
        http
            .formLogin()
                //.loginPage("/")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSucessHandler)
            .and()
                .authorizeHttpRequests() // 授权配置无需验证的请求
                .requestMatchers("/login").permitAll()
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
            .and().csrf().disable();
 
        http.headers().frameOptions().sameOrigin();
 
        return http.build();
    }

    // 用于密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
