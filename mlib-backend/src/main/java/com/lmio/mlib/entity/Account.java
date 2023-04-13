/*
 * @Author: lmio
 * @Date: 2023-04-06 20:52:09
 * @LastEditTime: 2023-04-13 14:00:51
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/entity/Account.java
 * @Description: 
 */
package com.lmio.mlib.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Account implements Serializable {
    private int id;
    private String role;
    private String username;
    private String password;
    private String email;
}
