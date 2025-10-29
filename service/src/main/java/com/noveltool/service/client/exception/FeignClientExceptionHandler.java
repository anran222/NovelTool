package com.noveltool.service.client.exception;

import com.noveltool.common.exception.ErrorCode;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Feign客户端异常处理器
 * 处理Feign调用过程中的各种异常
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Slf4j
@Component
public class FeignClientExceptionHandler {
    
    /**
     * 处理Feign异常并转换为业务异常
     */
    public FeignClientException handleFeignException(FeignException ex, String methodName) {
        ErrorCode errorCode;
        String errorMessage;
        
        // 根据异常类型判断错误类型
        if (ex instanceof FeignException.NotFound) {
            errorCode = ErrorCode.EXTERNAL_API_RESPONSE_ERROR;
            errorMessage = "外部接口资源不存在";
        } else if (ex instanceof FeignException.Unauthorized || ex instanceof FeignException.Forbidden) {
            errorCode = ErrorCode.EXTERNAL_API_AUTH_ERROR;
            errorMessage = "外部接口认证失败";
        } else if (ex instanceof FeignException.ServiceUnavailable) {
            errorCode = ErrorCode.EXTERNAL_API_ERROR;
            errorMessage = "外部接口服务不可用";
        } else if (ex instanceof FeignException.BadGateway || ex instanceof FeignException.GatewayTimeout) {
            errorCode = ErrorCode.EXTERNAL_API_TIMEOUT;
            errorMessage = "外部接口调用超时";
        } else if (ex.getMessage() != null && ex.getMessage().contains("timeout")) {
            errorCode = ErrorCode.EXTERNAL_API_TIMEOUT;
            errorMessage = "外部接口调用超时";
        } else if (ex.getMessage() != null && ex.getMessage().contains("Connection")) {
            errorCode = ErrorCode.EXTERNAL_API_CONNECTION_ERROR;
            errorMessage = "外部接口连接失败";
        } else {
            errorCode = ErrorCode.EXTERNAL_API_ERROR;
            errorMessage = "外部接口调用失败";
        }
        
        String detailMessage = String.format("%s - 方法: %s, 错误: %s", 
            errorMessage, methodName, ex.getMessage());
        
        log.error("Feign调用异常 - 方法: {}, 错误码: {}, 错误信息: {}", 
                  methodName, errorCode.getCode(), detailMessage, ex);
        
        return new FeignClientException(errorCode, detailMessage, ex);
    }
}

