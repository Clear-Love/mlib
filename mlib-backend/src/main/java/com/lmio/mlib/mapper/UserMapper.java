/*
 * @Author: lmio
 * @Date: 2023-04-12 18:58:43
 * @LastEditTime: 2023-04-12 19:44:37
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/mapper/UserMapper.java
 * @Description:
 */
package com.lmio.mlib.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.lmio.mlib.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MPJBaseMapper<User> {

}