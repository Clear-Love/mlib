package com.lmio.mlib.interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tika.Tika;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午3:20 2023/5/13
 * @Modified By:lmio
 */

@Component
public class UploadInterceptor implements HandlerInterceptor {
    private static final String[] ALLOWED_FILE_TYPES = {"application/epub+zip"};

    @Resource
    Tika tika;


    @Override
    public boolean preHandle(@Nullable HttpServletRequest request,@Nullable HttpServletResponse response,@Nullable Object handler) throws IOException {
        if (request instanceof MultipartHttpServletRequest multipartRequest) {

            // 获取上传的文件
            MultipartFile file = multipartRequest.getFile("file");

            // 使用 Tika 判断文件类型
            String fileType = null;
            if (file != null) {
                fileType = tika.detect(file.getInputStream());
            }

            // 检查文件类型是否允许上传
            if (!isFileTypeAllowed(fileType)) {
                if (response != null) {
                    response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported file type");
                }
                return false;
            }
        }

        return true;
    }

    private boolean isFileTypeAllowed(String fileType) {
        for (String allowedType : ALLOWED_FILE_TYPES) {
            if (allowedType.equals(fileType)) {
                return true;
            }
        }
        return false;
    }
}