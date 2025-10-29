package com.noveltool.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 小说生成器主应用类
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan("com.noveltool.service.mapper")
public class NovelToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelToolApplication.class, args);
    }
}
