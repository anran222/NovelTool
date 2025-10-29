package com.noveltool.common.exception;

import lombok.Getter;

/**
 * 业务异常基类
 * 所有业务异常都继承此类
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {
    
    /**
     * 错误码
     */
    private final ErrorCode errorCode;
    
    /**
     * 错误详情
     */
    private final String detail;
    
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.detail = null;
    }
    
    public BusinessException(ErrorCode errorCode, String detail) {
        super(errorCode.getDesc() + (detail != null ? ": " + detail : ""));
        this.errorCode = errorCode;
        this.detail = detail;
    }
    
    public BusinessException(ErrorCode errorCode, String detail, Throwable cause) {
        super(errorCode.getDesc() + (detail != null ? ": " + detail : ""), cause);
        this.errorCode = errorCode;
        this.detail = detail;
    }
    
    /**
     * 获取错误码
     */
    public Integer getCode() {
        return errorCode.getCode();
    }
    
    /**
     * 获取错误描述
     */
    @Override
    public String getMessage() {
        if (detail != null && !detail.isEmpty()) {
            return super.getMessage();
        }
        return errorCode.getDesc();
    }
}
