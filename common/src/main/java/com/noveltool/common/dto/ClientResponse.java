package com.noveltool.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客户端统一响应封装
 * 用于FeignClient调用外部接口的统一响应格式
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse<T> {
    
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
     * 响应时间戳
     */
    private Long timestamp;
    
    /**
     * 是否成功
     */
    public boolean isSuccess() {
        return code != null && code == 200;
    }
    
    /**
     * 静态方法：成功响应
     */
    public static <T> ClientResponse<T> success(T data) {
        ClientResponse<T> response = new ClientResponse<>();
        response.setCode(200);
        response.setMessage("操作成功");
        response.setData(data);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
    
    /**
     * 静态方法：成功响应（带消息）
     */
    public static <T> ClientResponse<T> success(String message, T data) {
        ClientResponse<T> response = new ClientResponse<>();
        response.setCode(200);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
    
    /**
     * 静态方法：失败响应
     */
    public static <T> ClientResponse<T> error(Integer code, String message) {
        ClientResponse<T> response = new ClientResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(null);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
}

