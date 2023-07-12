package com.lmio.mlib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmio.mlib.entity.BookList;
import com.lmio.mlib.mapper.BookListMapper;
import com.lmio.mlib.service.BookListService;
import org.springframework.stereotype.Service;

@Service
public class BookListServiceImpl extends ServiceImpl<BookListMapper, BookList> implements BookListService {
}
