/*
 * @Author: lmio
 * @Date: 2023-04-11 20:45:22
 * @LastEditTime: 2023-04-11 21:18:40
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/entity/RestBean.java
 * @Description:
 */
package com.lmio.mlib.Bean;

import lombok.Data;

@Data
public class RestBean<T> {
    private int status;
    private String message;
    private boolean success;
    private T data;

    private RestBean(int status, boolean success, String message) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = null;
    }

    private RestBean(int status, boolean success, String message, T data) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> RestBean<T> success(String message) {
        return new RestBean<>(200, true, message);
    }

    public static <T> RestBean<T> success(String message, T data) {
        return new RestBean<>(200, true, message, data);
    }

    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(200, true, "OK", data);
    }

    public static <T> RestBean<T> failure(String message) {
        return new RestBean<>(401, false, message);
    }

    public static <T> RestBean<T> failure(int status, T data) {
        return new RestBean<>(status, false, null, data);
    }
}