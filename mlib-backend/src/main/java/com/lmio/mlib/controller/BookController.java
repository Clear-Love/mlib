package com.lmio.mlib.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmio.mlib.Bean.RestBean;
import com.lmio.mlib.Bean.libPage;
import com.lmio.mlib.entity.Book;
import com.lmio.mlib.service.EbookService;
import jakarta.annotation.Resource;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午7:34 2023/5/2
 * @Modified By:lmio
 */

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Resource
    EbookService ebookService;

    @PostMapping("/search")
    public RestBean<IPage<Book>> searchBook(libPage page, @RequestParam("text") @Length(min = 1, max = 20) String text) {
        System.out.println(page);
        IPage<Book> books = ebookService.findBooksByText(new Page<>(page.getPage(), page.getLimit()), text);
        return RestBean.success(books);
    }
}