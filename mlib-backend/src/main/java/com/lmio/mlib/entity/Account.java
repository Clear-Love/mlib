/*
 * @Author: lmio
 * @Date: 2023-04-06 20:52:09
 * @LastEditTime: 2023-04-13 14:00:51
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/entity/Account.java
 * @Description: 
 */
package com.lmio.mlib.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class Account implements Serializable {
    private int user_id; // 用户uid
    private String role; // 角色
    private String username; // 用户名
    private String password; // 密码
    private String email; // 电子邮件
    private String phoneNumber; // 电话号码
    private String introduction; // 简介
    private String nickname; // 昵称
    private Date dateOfBirth; // 生日
    private int level; // 等级
    private int exp; // 经验
    private String avatar; // 头像url
    private String gender; // 性别
}
