package com.lmio.mlib.entity;

import lombok.Data;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午4:22 2023/4/26
 * @Modified By:lmio
 */

@Data
public class UserConfig {
    String username;
    String email;
    int level;
    int exp;
}
