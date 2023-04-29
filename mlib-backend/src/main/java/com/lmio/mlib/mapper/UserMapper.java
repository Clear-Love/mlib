/*
 * @Author: lmio
 * @Date: 2023-04-12 18:58:43
 * @LastEditTime: 2023-04-12 19:44:37
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/mapper/UserMapper.java
 * @Description: 
 */
package com.lmio.mlib.mapper;

import com.lmio.mlib.entity.UserConfig;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lmio.mlib.entity.Account;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);

    @Select("select * from db_account join user_config on db_account.username = user_config.username where db_account.username = #{username}")
    UserConfig findUserConfigByName(String username);

    @Insert("insert into user_config (username, level, exp) values (#{userConfig.username}, 0, 0)")
    int createUserConfig(String username);

    @Insert("insert into db_account (username, password, email, role) values (#{username}, #{password}, #{email}, 'user')")
    int createAccount(String username, String password, String email);

    @Update("update db_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String email, String password);
}