package com.lmio.mlib.mapper;

import com.lmio.mlib.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;

/**
 * @Author: lmio
 * @Description: TODO 评论sql
 * @Date: 下午3:00 2023/5/2
 * @Modified By:lmio
 */

@Mapper
public interface CommentMapper {
    @Select("select * from comment where book_id = #{bookId}")
    Comment findCommentByBookId(Integer bookId);

    @Select("<script>"
            + "SELECT * FROM user_comment_book c WHERE 1=1 "
            + "<if test='bookId != null'>AND c.book_id = #{bookId}</if>"
            + "<if test='time != null'>AND c.time &lt; #{time}</if>"
            + "<if test='type != null'>AND c.type = #{type}</if>"
            + "</script>")
    Comment findCommentByCondition(Integer bookId, Boolean type, Date time);
}
