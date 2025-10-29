package com.noveltool.service.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson配置类
 * 配置API层：接收下划线参数并转换为驼峰对象，响应使用驼峰格式
 * 与Client层保持一致：都支持下划线转驼峰的转换
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Configuration
public class JacksonConfig {
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 配置API层的ObjectMapper
     * - 接收请求时：下划线格式自动转换为驼峰对象
     * - 发送响应时：使用驼峰格式（不设置命名策略，使用默认驼峰）
     * 
     * 注意：由于PropertyNamingStrategy同时影响序列化和反序列化，
     * 如果需要不同的策略，需要通过application.yml配置或使用自定义转换器
     * 
     * 不使用@Primary，让Spring Boot自动配置的ObjectMapper作为主bean
     * 通过Jackson2ObjectMapperBuilderCustomizer来自定义配置，避免与自动配置冲突
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // JavaTimeModule配置（Spring Boot 3.x已经自动注册，这里只是确保日期格式正确）
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, 
                new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
            javaTimeModule.addDeserializer(LocalDateTime.class, 
                new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
            builder.modules(javaTimeModule);
            
            // 日期格式配置已经在application.yml中配置
            // 命名策略也在application.yml中配置
            // 这里只需要确保自定义模块已注册
        };
    }
}
