/*
 * @Author: lmio
 * @Date: 2023-04-23 16:37:27
 * @LastEditTime: 2023-04-23 16:42:00
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/service/AuthorizeService.java
 * @Description: 
 */
package com.lmio.mlib.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {
    boolean sendValidateEmail(String email);
}

