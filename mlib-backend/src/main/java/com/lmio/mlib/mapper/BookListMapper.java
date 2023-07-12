package com.lmio.mlib.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.lmio.mlib.entity.BookList;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: lmio
 * @Description: TODO 书单操作sql
 * @Date: 下午7:34 2023/5/1
 * @Modified By:lmio
 */

@Mapper
public interface BookListMapper extends MPJBaseMapper<BookList> {
    
}
