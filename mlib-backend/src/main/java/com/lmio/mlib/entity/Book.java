package com.lmio.mlib.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午6:49 2023/4/30
 * @Modified By:lmio
 */

@Data
public class Book {
    private String isbn; // ISBN编号（主码）
    private String title; // 书名
    private String author; // 作者
    private String publisher; // 出版社名称（外码）
    private Date publishDate; // 出版时间
    private String language; // 语言
    private int wordCount; // 字数
    private int downloadCount; // 下载次数
    private long fileSize; // 文件大小
    private String bookFormat; // 图书格式（外码）
    private String introduction; // 简介
}
