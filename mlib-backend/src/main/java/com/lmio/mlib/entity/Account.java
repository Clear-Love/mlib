package com.lmio.mlib.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("db_account")
public class Account {
    @TableId(type = IdType.AUTO)
    private Integer userId; // 用户uid
    private String roleId; // 角色id
    private String username; // 用户名
    private String password; //密码
    private String email; // 电子邮件
}
