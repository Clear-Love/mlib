package com.lmio.mlib.mapper;

import com.lmio.mlib.entity.Book;
import com.lmio.mlib.entity.BookPath;
import org.apache.ibatis.annotations.Insert;
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
    @Select("select * from book where title LIKE CONCAT('%', #{title}, '%')")
    List<Book> findBookByTitle(String title);

    @Select("select * from book where book_id = #{bookId}")
    List<Book> findBookById(int id);

    @Select("select * from book where ISBN LIKE CONCAT('%', #{ISBN}, '%')")
    List<Book> findBookByISBN(String ISBN);

    @Select("select * from book where author LIKE CONCAT('%', #{author}, '%')")
    List<Book> findBooksByAuthor(String author);


    @Select("<script>"
            + "SELECT * FROM book WHERE 1=1 "
            + "<if test='bookName != null'>AND title LIKE CONCAT('%', #{title}, '%')</if>"
            + "<if test='publisher != null'>AND publisher LIKE CONCAT('%', #{publisher}, '%')</if>"
            + "<if test='author != null'>AND author LIKE CONCAT('%', #{author}, '%')</if>"
            + "</script>")
    List<Book> findBooksByConditions(String title, String publisher, String author);

    @Select("SELECT * FROM book WHERE " +
            "isbn LIKE CONCAT('%', #{text}, '%') OR " +
            "title LIKE CONCAT('%', #{text}, '%') OR " +
            "publisher LIKE CONCAT('%', #{text}, '%') OR " +
            "author LIKE CONCAT('%', #{text}, '%')")
    List<Book> findBooksByText(String text);

    @Select("select * from book_format_reaction bfr join book_format bf " +
            "on bfr.format_id = bf.format_id " +
            "where bfr.book_id = #{bookId}")
    List<BookPath> findBookPathByBookId(int bookId);

    @Insert("insert into book_format_relation (book_id, format_id, location_url, uuid) " +
            "values (#{bookId}, " +
            "(select format_id from book_format where format_name = #{format}), " +
            "#{locationUrl}, " +
            "#{uuid})")
    int addBookPath(int bookId, String format, String locationUrl, String uuid);

}