/*
 * @Author: lmio
 * @Date: 2023-04-06 20:52:09
 * @LastEditTime: 2023-04-13 14:00:51
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/entity/Account.java
 * @Description:
 */
package com.lmio.mlib.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("db_account")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId; // 用户uid
    private String roleId; // 角色
    private String username; // 用户名
    private String email; // 电子邮件
    private String phoneNumber; // 电话号码
    private String introduction; // 简介
    private String nickname; // 昵称
    private Integer level; // 等级
    private Integer exp; // 经验
    private String avatar; // 头像url
    private String gender; // 性别
}
