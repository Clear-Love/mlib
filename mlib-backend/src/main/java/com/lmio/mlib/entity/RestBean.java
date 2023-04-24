/*
 * @Author: lmio
 * @Date: 2023-04-11 20:45:22
 * @LastEditTime: 2023-04-11 21:18:40
 * @FilePath: /mlib/mlib-backend/src/main/java/com/lmio/mlib/entity/RestBean.java
 * @Description: 
 */
package com.lmio.mlib.entity;

import lombok.Data;

@Data
public class RestBean<T> {
    private int status;

    private T message;

    private boolean success;

    private RestBean(int status, boolean success, T message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    public static <T> RestBean<T> success() {
        return new RestBean<>(200, true, null);
    }

    public static <T> RestBean<T> success(T message) {
        return new RestBean<>(200, true, message);
    }

    public static <T> RestBean<T> success(int status, T message) {
        return  new RestBean<>(status, true, message);
    }

    public static <T> RestBean<T> failure() {
        return new RestBean<>(401, false, null);
    }

    public static <T> RestBean<T> failure(T message) {
        return new RestBean<>(401, false, message);
    }

    public static <T> RestBean<T> failure(int status, T message) {
        return new RestBean<>(status, false, message);
    }
}