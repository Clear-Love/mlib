package com.lmio.mlib.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmio.mlib.entity.Book;
import com.lmio.mlib.mapper.BookMapper;
import com.lmio.mlib.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午3:35 2023/5/13
 * @Modified By:lmio
 */

@Service
public class EbookServiceImpl extends ServiceImpl<BookMapper, Book> implements EbookService {

    @Resource
    BookMapper mapper;


    @Override
    public IPage<Book> findBookByTitle(Page<Book> page, String title) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        return mapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Book> findBookByISBN(Page<Book> page, String ISBN) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isbn", ISBN);
        return mapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Book> findBooksByAuthor(Page<Book> page, String author) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author", author);
        return mapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Book> findBooksByText(Page<Book> page, String text) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("author", text)
                .or()
                .like("title", text)
                .or()
                .like("isbn", text);
        return mapper.selectPage(page, queryWrapper);
    }
}