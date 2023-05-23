package com.lmio.mlib.service.impl;

import com.lmio.mlib.service.EbookMetadataService;
import jakarta.annotation.Resource;
import org.apache.tika.parser.Parser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午3:35 2023/5/13
 * @Modified By:lmio
 */

@Service
public class EbookMetadataServiceimpl implements EbookMetadataService {

    @Resource
    Parser parser;

    @Override
    public String addEpubBook(MultipartFile ebookFile) {
        return null;
    }

    @Override
    public String addPdfBook(MultipartFile ebookFile) {
        return null;
    }
}
