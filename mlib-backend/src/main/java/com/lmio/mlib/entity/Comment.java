package com.lmio.mlib.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午5:47 2023/5/1
 * @Modified By:lmio
 */

@Data
public class Comment implements Serializable {
    private int comment_id; // 评论id
    private int book_id; // 图书id
    private int user_id; // 用户id
    private String content; // 评论内容
    private Data time; // 评论时间
    private String type; // 评论类型
}
