package com.lmio.mlib.entity;

import lombok.Data;

@Data
public class Account {
    private int user_id; // 用户uid
    private String role; // 角色
    private String username; // 用户名
    private String password; //密码
    private String email; // 电子邮件
}
