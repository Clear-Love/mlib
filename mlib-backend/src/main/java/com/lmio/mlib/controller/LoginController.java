/*
 * @Author: lmio
 * @Date: 2023-04-04 19:20:26
 * @LastEditTime: 2023-04-04 19:24:43
 * @FilePath: /mlib/src/main/java/com/lmio/mlib/controller/LoginController.java
 * @Description: 
 */
package com.lmio.mlib.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("login")
    public String login() {
        return "登陆页面";
    }
}