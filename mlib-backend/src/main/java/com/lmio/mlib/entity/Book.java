package com.lmio.mlib.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午6:49 2023/4/30
 * @Modified By:lmio
 */

@Data
public class Book {
    @TableId(type = IdType.AUTO)
    private String isbn; // ISBN编号
    private Integer bookId;
    private String title; // 书名
    private String author; // 作者
    private String publisher; // 出版社名称（外码）
    private String publishDate; // 出版时间
    private String language; // 语言
    private Integer collectCount; // 收藏次数
    private String description; // 简介
    private String price; // 价格
    private String coverImage; //封面
    private String ratingNum; //评分
}
