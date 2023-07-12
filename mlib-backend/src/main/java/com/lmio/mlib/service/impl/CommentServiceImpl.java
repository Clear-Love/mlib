package com.lmio.mlib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmio.mlib.entity.Comment;
import com.lmio.mlib.mapper.CommentMapper;
import com.lmio.mlib.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
