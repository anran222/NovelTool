package com.noveltool.service.aspect;

import com.noveltool.common.dto.DataResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 响应统一包装切面
 * 自动将Controller方法的返回值包装为DataResponse
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Aspect
@Component
@Order(1)
public class ResponseAspect {
    
    /**
     * 拦截所有Controller方法的返回值
     * 如果返回值已经是DataResponse类型，则直接返回
     * 否则自动包装为DataResponse
     */
    @Around("execution(* com.noveltool.service.controller.*.*(..))")
    public Object wrapResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        
        // 如果返回值已经是DataResponse类型，直接返回
        if (result instanceof DataResponse) {
            return result;
        }
        
        // 如果返回值为null，包装为成功响应
        if (result == null) {
            return DataResponse.success();
        }
        
        // 其他情况，自动包装为DataResponse
        return DataResponse.success(result);
    }
}

