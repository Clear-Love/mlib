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
    public String addBook(MultipartFile ebookFile, int bookId) {
        String uuid = String.valueOf(bookId + 1000000);
        String originalFilename = ebookFile.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFilename);
        String filePath = "contain/books/" + uuid + "." + fileExtension;
        File file = new File(filePath);
        // 检查目录是否存在，如果不存在则创建
        File directory = file.getParentFile();
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                return "无法创建目录";
            }
        }
        if (mapper.addBookPath(bookId, fileExtension, filePath, uuid) == 0) {
            return "插入失败";
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(ebookFile.getBytes());
            fos.close();
        } catch (IOException e) {
            return e.getMessage();
        }
        return null;
    }

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