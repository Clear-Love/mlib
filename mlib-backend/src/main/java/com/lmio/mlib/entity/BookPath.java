package com.lmio.mlib.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午4:11 2023/5/2
 * @Modified By:lmio
 */

@Data
public class BookPath implements Serializable {
    private int bookID;
    private String locationUrl;
    private String formatName;
}
