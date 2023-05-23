package com.lmio.mlib.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午3:31 2023/5/13
 * @Modified By:lmio
 */
public interface EbookMetadataService {
    String addEpubBook(MultipartFile ebookFile);
    String addPdfBook(MultipartFile ebookFile);
}
