package com.noveltool.service.client.config;

import com.noveltool.common.exception.ErrorCode;
import com.noveltool.service.client.exception.FeignClientException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Feign错误解码器
 * 统一处理Feign调用失败的情况
 * 注意：不要使用@Component注解，由FeignConfig中的@Bean方法注册
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    
    private final ErrorDecoder defaultErrorDecoder = new Default();
    
    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorCode errorCode;
        String errorMessage;
        
        // 根据HTTP状态码确定错误类型
        switch (response.status()) {
            case 400:
                errorCode = ErrorCode.PARAM_ERROR;
                errorMessage = "请求参数错误";
                break;
            case 401:
                errorCode = ErrorCode.EXTERNAL_API_AUTH_ERROR;
                errorMessage = "外部接口认证失败";
                break;
            case 403:
                errorCode = ErrorCode.EXTERNAL_API_AUTH_ERROR;
                errorMessage = "外部接口禁止访问";
                break;
            case 404:
                errorCode = ErrorCode.EXTERNAL_API_RESPONSE_ERROR;
                errorMessage = "外部接口资源不存在";
                break;
            case 408:
            case 504:
                errorCode = ErrorCode.EXTERNAL_API_TIMEOUT;
                errorMessage = "外部接口调用超时";
                break;
            case 429:
                errorCode = ErrorCode.EXTERNAL_API_RATE_LIMIT;
                errorMessage = "外部接口调用频率超限";
                break;
            case 500:
            case 502:
            case 503:
                errorCode = ErrorCode.EXTERNAL_API_ERROR;
                errorMessage = "外部接口服务器错误";
                break;
            default:
                errorCode = ErrorCode.EXTERNAL_API_ERROR;
                errorMessage = "外部接口调用失败，状态码: " + response.status();
        }
        
        // 尝试读取响应体获取详细错误信息
        String responseBody = null;
        if (response.body() != null) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {
                responseBody = reader.lines().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                log.warn("读取Feign错误响应体失败: {}", e.getMessage());
            }
        }
        
        String detailMessage = errorMessage;
        if (responseBody != null && !responseBody.isEmpty()) {
            detailMessage += "，响应内容: " + responseBody;
        }
        
        log.error("Feign调用失败 - 方法: {}, 状态码: {}, 错误信息: {}", 
                  methodKey, response.status(), detailMessage);
        
        return new FeignClientException(errorCode, detailMessage);
    }
}
