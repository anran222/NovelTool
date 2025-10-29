package com.noveltool.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举
 * 统一管理系统中的所有错误码
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    
    // 通用错误码 1000-1999
    SUCCESS(200, "操作成功"),
    SYSTEM_ERROR(1000, "系统错误"),
    PARAM_ERROR(1001, "参数错误"),
    PARAM_NULL(1002, "参数不能为空"),
    PARAM_INVALID(1003, "参数格式不正确"),
    
    // 业务错误码 2000-2999
    NOVEL_NOT_FOUND(2001, "小说不存在"),
    NOVEL_ALREADY_EXISTS(2002, "小说已存在"),
    NOVEL_TITLE_EMPTY(2003, "小说标题不能为空"),
    NOVEL_AUTHOR_EMPTY(2004, "作者不能为空"),
    
    // 数据访问错误码 3000-3999
    DATABASE_ERROR(3001, "数据库操作失败"),
    DATABASE_CONNECTION_ERROR(3002, "数据库连接失败"),
    DATA_NOT_FOUND(3003, "数据不存在"),
    
    // 外部接口错误码 4000-4999
    EXTERNAL_API_ERROR(4001, "外部接口调用失败"),
    EXTERNAL_API_TIMEOUT(4002, "外部接口调用超时"),
    EXTERNAL_API_CONNECTION_ERROR(4003, "外部接口连接失败"),
    EXTERNAL_API_RESPONSE_ERROR(4004, "外部接口响应错误"),
    EXTERNAL_API_AUTH_ERROR(4005, "外部接口认证失败"),
    EXTERNAL_API_RATE_LIMIT(4006, "外部接口调用频率超限"),
    
    // 权限错误码 5000-5999
    UNAUTHORIZED(5001, "未授权"),
    FORBIDDEN(5002, "禁止访问"),
    TOKEN_INVALID(5003, "Token无效"),
    TOKEN_EXPIRED(5004, "Token已过期"),
    
    // 文件操作错误码 6000-6999
    FILE_UPLOAD_ERROR(6001, "文件上传失败"),
    FILE_DOWNLOAD_ERROR(6002, "文件下载失败"),
    FILE_NOT_FOUND(6003, "文件不存在"),
    FILE_SIZE_EXCEEDED(6004, "文件大小超出限制"),
    
    // 未知错误
    UNKNOWN_ERROR(9999, "未知错误");
    
    /**
     * 错误码
     */
    private final Integer code;
    
    /**
     * 错误描述
     */
    private final String desc;
    
    /**
     * 根据错误码获取错误码枚举
     */
    public static ErrorCode getByCode(Integer code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }
        return UNKNOWN_ERROR;
    }
}

