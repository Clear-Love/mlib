package com.lmio.mlib.mapper;

import com.lmio.mlib.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: lmio
 * @Description: TODO 图书查询
 * @Date: 下午7:07 2023/4/30
 * @Modified By:lmio
 */

@Mapper
public interface BookMapper {
    @Select("select * from book where book_name = #{bookName}")
    List<Book> findBookByBookName(String bookName);

    @Select("select * from book where publisher_id = #{publisher_id}")
    List<Book> findBooksByPublisherName(String publisher_id);

    @Select("select * from book where ISBN = #{ISBN}")
    Book findBookByISBN(String ISBN);

    @Select("select * from book where author = #{author}")
    List<Book> findBooksByAuthor(String author);

    @Select("SELECT publisher_name FROM publisher WHERE publisher_id = #{id}")
    String findPublisherNameById(int id);

    @Select("<script>"
            + "SELECT * FROM book WHERE 1=1 "
            + "<if test='bookName != null'>AND book_name = #{bookName}</if>"
            + "<if test='publisher != null'>AND publisher_id = #{publisher_id}</if>"
            + "<if test='author != null'>AND author = #{author}</if>"
            + "</script>")
    List<Book> findBooksByConditions(String bookName, String publisher_id, String author);

}
