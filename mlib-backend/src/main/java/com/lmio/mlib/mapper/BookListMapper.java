package com.lmio.mlib.mapper;

import com.lmio.mlib.entity.BookList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: lmio
 * @Description: TODO 书单操作sql
 * @Date: 下午7:34 2023/5/1
 * @Modified By:lmio
 */

@Mapper
public interface BookListMapper {
    @Select("select * from bookList where list_name = #{name}")
    BookList findBookListByName(String name);

    @Select("select * form bookList b join db_account a on b.create_user = a.user_id where a.username = #{name}")
    BookList findBookListByUser(String name);

    @Select("select * form bookList b join db_account a on b.create_user = a.user_id " +
            "where a.username = #{text} or b.list_name = #{text}")
    BookList findBookListByUserOrName(String text);

    @Insert("insert into bookList_book_relation (list_id, book_id) values (#{bookListId}, #{bookId})")
    int bookListAddBook(int bookListId, int bookId);

    @Insert("insert into bookList (create_user, list_name, description) " +
            "values (#{userId}, #{bookListName}, #{description})")
    int createBookList(int userId, String bookListName, String description);
}
