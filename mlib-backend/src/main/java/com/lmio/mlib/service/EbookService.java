package com.lmio.mlib.service;

import com.lmio.mlib.entity.Book;
import com.lmio.mlib.entity.BookPath;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午3:31 2023/5/13
 * @Modified By:lmio
 */
public interface EbookService {

    List<Book> findBookByTitle(String title);

    List<Book> findBookByISBN(String ISBN);

    List<Book> findBookById(int bookId);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByConditions(String bookName, String publisher, String author);

    List<Book> findBooksByText(String text);

    List<BookPath> findBookPathByBookId(int bookId);

}