package com.lmio.mlib.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;

@Data
public class Role {
    @TableId(type = IdType.AUTO)
    Integer roleId;
    String role;
    Date createDate;
}
