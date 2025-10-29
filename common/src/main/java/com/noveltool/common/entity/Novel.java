package com.noveltool.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 小说实体类
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Novel {
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 小说标题
     */
    private String title;
    
    /**
     * 小说内容
     */
    private String content;
    
    /**
     * 作者
     */
    private String author;
    
    /**
     * 分类
     */
    private String category;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
