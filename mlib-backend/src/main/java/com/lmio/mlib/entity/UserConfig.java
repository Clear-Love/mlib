package com.lmio.mlib.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午4:22 2023/4/26
 * @Modified By:lmio
 */

@Data
public class UserConfig {
    private int userId;
    private String phoneNumber;
    private String introduction;
    private String address;
    private String nickname;
    private Date dateOfBirth;
    private int level;
    private int exp;
    private String avatar;
    private String gender;
}
