package com.noveltool.service.exception;

import com.noveltool.common.dto.DataResponse;
import com.noveltool.common.exception.BusinessException;
import com.noveltool.common.exception.ErrorCode;
import com.noveltool.service.client.exception.FeignClientException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public DataResponse<Void> handleBusinessException(BusinessException ex) {
        return DataResponse.error(ex.getCode(), ex.getMessage());
    }
    
    /**
     * 处理Feign调用异常
     */
    @ExceptionHandler(FeignClientException.class)
    public DataResponse<Void> handleFeignException(FeignClientException ex) {
        return DataResponse.error(ex.getCode(), ex.getMessage());
    }
    
    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataResponse<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return DataResponse.error(
            ErrorCode.PARAM_INVALID.getCode(), 
            ErrorCode.PARAM_INVALID.getDesc(), 
            errors
        );
    }
    
    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public DataResponse<Void> handleRuntimeException(RuntimeException ex) {
        // 如果是BusinessException的子类，已经被上面的处理器捕获
        if (ex instanceof BusinessException) {
            return handleBusinessException((BusinessException) ex);
        }
        return DataResponse.error(
            ErrorCode.SYSTEM_ERROR.getCode(), 
            ex.getMessage()
        );
    }
    
    /**
     * 处理通用异常
     */
    @ExceptionHandler(Exception.class)
    public DataResponse<Void> handleGenericException(Exception ex) {
        return DataResponse.error(
            ErrorCode.SYSTEM_ERROR.getCode(), 
            "服务器内部错误: " + ex.getMessage()
        );
    }
}
