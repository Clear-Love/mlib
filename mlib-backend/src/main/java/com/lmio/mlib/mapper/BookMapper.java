package com.lmio.mlib.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.lmio.mlib.entity.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: lmio
 * @Description: TODO 图书查询
 * @Date: 下午7:07 2023/4/30
 * @Modified By:lmio
 */

@Mapper
public interface BookMapper extends MPJBaseMapper<Book> {

}