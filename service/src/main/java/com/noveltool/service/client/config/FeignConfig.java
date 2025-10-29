package com.noveltool.service.client.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Feign客户端配置类
 * 支持下划线转驼峰、日期类型处理
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Configuration
public class FeignConfig {
    
    /**
     * 创建Feign响应解码器使用的ObjectMapper
     * 将下划线格式的响应转换为驼峰格式
     * 注意：不作为Spring Bean注册，避免与Spring Boot自动配置的ObjectMapper冲突
     */
    private ObjectMapper createFeignDecoderObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        
        // 注册Java 8时间模块
        mapper.registerModule(new JavaTimeModule());
        
        // 禁用将日期写为时间戳
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        // 忽略未知属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        // 配置日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        mapper.setDateFormat(dateFormat);
        
        // 配置命名策略：接收响应时，下划线转驼峰
        // 这样可以将外部API返回的下划线格式响应转换为驼峰格式
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        
        return mapper;
    }
    
    /**
     * 创建Feign请求编码器使用的ObjectMapper
     * 发送请求时，将驼峰格式转换为下划线格式
     * 注意：不作为Spring Bean注册，避免与Spring Boot自动配置的ObjectMapper冲突
     */
    private ObjectMapper createFeignEncoderObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        
        // 注册Java 8时间模块
        mapper.registerModule(new JavaTimeModule());
        
        // 禁用将日期写为时间戳
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        // 忽略未知属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        // 配置日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        mapper.setDateFormat(dateFormat);
        
        // 配置命名策略：发送请求时，驼峰转下划线
        // 这样可以将驼峰格式的对象转换为下划线格式发送给外部API
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        
        return mapper;
    }
    
    /**
     * Feign响应解码器
     * 将下划线格式的响应转换为驼峰格式
     */
    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder(createFeignDecoderObjectMapper());
    }
    
    /**
     * Feign请求编码器
     * 支持日期类型和各种参数格式，将驼峰转换为下划线
     */
    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder(createFeignEncoderObjectMapper());
    }
    
    /**
     * Feign错误解码器
     * 统一处理Feign调用失败的情况
     */
    @Bean
    public ErrorDecoder feignErrorDecoder() {
        return new FeignErrorDecoder();
    }
}

