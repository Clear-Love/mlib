package com.lmio.mlib.entity;

/*
  @Author: lmio
 * @Description: TODO
 * @Date: 下午6:52 2023/5/1
 * @Modified By:lmio
 */

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class BookList implements Serializable {
    private int listId; // 书单id
    private String listName; // 书单名
    private String description; // 简介
    private Date createTime; // 创建时间
    private int createUser; // 创建者
    private int collectCount; // 收藏数
    private int bookCount; // 包含图书数
    private List<Book> books; // 图书列表
}
