package com.noveltool.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 小说数据传输对象
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Data
public class NovelDTO {
    
    private Long id;
    
    @NotBlank(message = "标题不能为空")
    @Size(max = 200, message = "标题长度不能超过200个字符")
    private String title;
    
    private String content;
    
    @Size(max = 100, message = "作者名称长度不能超过100个字符")
    private String author;
    
    @Size(max = 50, message = "分类长度不能超过50个字符")
    private String category;
}
