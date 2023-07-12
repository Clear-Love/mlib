package com.lmio.mlib.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lmio.mlib.entity.Book;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午3:31 2023/5/13
 * @Modified By:lmio
 */
public interface EbookService extends IService<Book> {

    IPage<Book> findBookByTitle(Page<Book> page, String title);

    IPage<Book> findBookByISBN(Page<Book> page, String ISBN);

    IPage<Book> findBooksByAuthor(Page<Book> page, String author);

    IPage<Book> findBooksByText(Page<Book> page, String text);
}