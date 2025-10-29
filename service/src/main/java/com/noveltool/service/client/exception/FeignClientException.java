package com.noveltool.service.client.exception;

import com.noveltool.common.exception.BusinessException;
import com.noveltool.common.exception.ErrorCode;

/**
 * Feign调用异常
 * 
 * @author NovelTool
 * @since 1.0.0
 */
public class FeignClientException extends BusinessException {
    
    public FeignClientException(ErrorCode errorCode) {
        super(errorCode);
    }
    
    public FeignClientException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
    
    public FeignClientException(ErrorCode errorCode, String detail, Throwable cause) {
        super(errorCode, detail, cause);
    }
}

