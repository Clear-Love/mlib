package com.lmio.mlib.service.impl;

import com.lmio.mlib.entity.Book;
import com.lmio.mlib.entity.BookPath;
import com.lmio.mlib.mapper.BookMapper;
import com.lmio.mlib.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午3:35 2023/5/13
 * @Modified By:lmio
 */

@Service
public class EbookServiceimpl implements EbookService {

    @Resource
    BookMapper mapper;

    @Override
    public List<Book> findBookByTitle(String title) {
        return mapper.findBookByTitle(title);
    }

    @Override
    public List<Book> findBookByISBN(String ISBN) {
        return mapper.findBookByISBN(ISBN);
    }

    @Override
    public List<Book> findBookById(int bookId) {
        return mapper.findBookById(bookId);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return mapper.findBooksByAuthor(author);
    }

    @Override
    public List<Book> findBooksByConditions(String title, String publisher, String author) {
        return mapper.findBooksByConditions(title, publisher, author);
    }

    @Override
    public List<Book> findBooksByText(String text) {
        return mapper.findBooksByText(text);
    }

    @Override
    public List<BookPath> findBookPathByBookId(int bookId) {
        return null;
    }
}