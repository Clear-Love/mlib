/*
 * @Author: lmio
 * @Date: 2023-04-12 18:58:43
 * @LastEditTime: 2023-04-12 19:44:37
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/mapper/UserMapper.java
 * @Description: 
 */
package com.lmio.mlib.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lmio.mlib.entity.Account;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("SELECT a.*, r.role FROM db_account a JOIN role r ON a.role_id = r.role_id" +
            " where a.username = #{text} or a.email = #{text}")
    Account findAccountByNameOrEmail(String text);

    @Insert("insert into db_account (username, password, email, role_id) values " +
            "(#{userName}, #{password}, #{email}, " +
            "(SELECT role_id FROM role WHERE role = #{roleName}))")
    int createAccount(String userName, String password, String email, String roleName);

    @Update("update db_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String email, String password);

    @Insert("insert into user_collection_books (user_id, book_id) values (#{userId}, #{bookId})")
    int collectBook(int userId, int bookId);

    @Insert("insert into user_collection_bookLists (user_id, bookList_id) values (#{userId}, #{bookListId})")
    int collectBookList(int userId, int bookListId);

    @Insert("insert into user_comment_book (user_id, book_id, content, type) values " +
            "(#{userId}, #{bookId}, #{content}, #{isLong})")
    int commentBook(int userId, int bookId, String content, boolean isLong);

    @Insert("insert into user_comment_bookList (user_id, bookList_id, content) values " +
            "(#{userId}, #{bookListId}, #{content})")
    int commentBookList(int userId, int bookListId, String content);
}