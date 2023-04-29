package com.lmio.mlib.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午7:10 2023/4/29
 * @Modified By:lmio
 */
public interface MapperService {
    String register(String username, String password, String email);
}
