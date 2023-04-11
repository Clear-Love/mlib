/*
 * @Author: lmio
 * @Date: 2023-04-04 18:15:00
 * @LastEditTime: 2023-04-04 18:15:02
 * @FilePath: /mlib/src/main/java/com/lmio/mlib/controller/TestController.java
 * @Description: 测试接口
 */
package com.lmio.mlib.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }
}