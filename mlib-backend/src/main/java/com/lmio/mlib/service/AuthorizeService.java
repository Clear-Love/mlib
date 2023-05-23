/*
 * @Author: lmio
 * @Date: 2023-04-23 16:37:27
 * @LastEditTime: 2023-05-02 20:41:50
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/service/AuthorizeService.java
 * @Description: 
 */
package com.lmio.mlib.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {
    String sendValidateEmail(String email, boolean hasAccount);
    String validateUsername(String name);
    String validateAndRegister(String username, String password, String email, String code);
    String validateOnly(String email, String code);
    boolean resetPassword(String email, String password);
}