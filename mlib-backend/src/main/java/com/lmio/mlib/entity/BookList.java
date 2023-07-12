package com.lmio.mlib.entity;

/*
  @Author: lmio
 * @Description: TODO
 * @Date: 下午6:52 2023/5/1
 * @Modified By:lmio
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BookList {
    @TableId(type = IdType.AUTO)
    private Integer listId; // 书单id
    private String listName; // 书单名
    private String description; // 简介
    private Date createTime; // 创建时间
    private Integer createUser; // 创建者
    private Integer collectCount; // 收藏数
    private Integer bookCount; // 包含图书数
}
