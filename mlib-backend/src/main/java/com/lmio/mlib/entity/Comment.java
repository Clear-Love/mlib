package com.lmio.mlib.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午5:47 2023/5/1
 * @Modified By:lmio
 */

@Data
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer commentId; // 评论id
    private Integer bookId; // 图书id
    private Integer userId; // 用户id
    private String content; // 评论内容
    private Date time; // 评论时间
    private String type; // 评论类型
}
