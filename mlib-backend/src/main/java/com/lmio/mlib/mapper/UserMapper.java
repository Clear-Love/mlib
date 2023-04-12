/*
 * @Author: lmio
 * @Date: 2023-04-12 18:58:43
 * @LastEditTime: 2023-04-12 19:44:37
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/mapper/UserMapper.java
 * @Description: 
 */
package com.lmio.mlib.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lmio.mlib.entity.Account;

@Mapper
public interface UserMapper {
    @Select("select * from db_account where username = #{text} or email = #{text}")
    public Account findAccountByNameOrEmail(String text);
}