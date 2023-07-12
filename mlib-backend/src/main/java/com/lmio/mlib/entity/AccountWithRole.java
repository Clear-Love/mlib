package com.lmio.mlib.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountWithRole extends Account {
    private String role; // 角色id

    public AccountWithRole(String role, String username, String password, String email) {
        this.role = role;
    }
}
