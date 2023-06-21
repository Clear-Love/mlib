package com.lmio.mlib.controller;

import com.lmio.mlib.entity.Book;
import com.lmio.mlib.entity.RestBean;
import com.lmio.mlib.mapper.BookMapper;
import com.lmio.mlib.service.EbookService;
import jakarta.annotation.Resource;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

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

    @PostMapping ("/search")
    public RestBean<List<Book>> searchBook(@RequestParam("text") @Length(min = 1, max = 20) String text) {
        System.out.println(text);
        List<Book> books = ebookService.findBooksByText(text);
        return RestBean.success(books);
    }
}