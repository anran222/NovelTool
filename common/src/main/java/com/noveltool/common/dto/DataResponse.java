package com.noveltool.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一数据响应封装
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> {
    
    /**
     * 响应码
     */
    private Integer code;
    
    /**
     * 响应消息
     */
    private String message;
    
    /**
     * 响应数据
     */
    private T data;
    
    /**
     * 成功响应（带数据）
     */
    public static <T> DataResponse<T> success(T data) {
        return new DataResponse<>(200, "操作成功", data);
    }
    
    /**
     * 成功响应（带消息和数据）
     */
    public static <T> DataResponse<T> success(String message, T data) {
        return new DataResponse<>(200, message, data);
    }
    
    /**
     * 成功响应（无数据）
     */
    public static DataResponse<Void> success() {
        return new DataResponse<>(200, "操作成功", null);
    }
    
    /**
     * 成功响应（只有消息）
     */
    public static DataResponse<Void> success(String message) {
        return new DataResponse<>(200, message, null);
    }
    
    /**
     * 错误响应
     */
    public static <T> DataResponse<T> error(String message) {
        return new DataResponse<>(500, message, null);
    }
    
    /**
     * 错误响应（带错误码）
     */
    public static <T> DataResponse<T> error(Integer code, String message) {
        return new DataResponse<>(code, message, null);
    }
    
    /**
     * 错误响应（带错误码和数据）
     */
    public static <T> DataResponse<T> error(Integer code, String message, T data) {
        return new DataResponse<>(code, message, data);
    }
}

