package com.lmio.mlib.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmio.mlib.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: lmio
 * @Description: TODO 评论
 * @Date: 下午3:00 2023/5/2
 * @Modified By:lmio
 */

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}