package com.noveltool.service.mapper;

import com.noveltool.common.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小说MyBatis Mapper接口
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Mapper
public interface NovelMapper {
    
    /**
     * 查询所有小说
     */
    List<Novel> findAll();
    
    /**
     * 根据ID查询小说
     */
    Novel findById(@Param("id") Long id);
    
    /**
     * 插入小说
     */
    int insert(Novel novel);
    
    /**
     * 更新小说
     */
    int update(Novel novel);
    
    /**
     * 根据ID删除小说
     */
    int deleteById(@Param("id") Long id);
    
    /**
     * 检查小说是否存在
     */
    boolean existsById(@Param("id") Long id);
    
    /**
     * 根据标题模糊查询
     */
    List<Novel> findByTitleContaining(@Param("title") String title);
    
    /**
     * 根据作者查询
     */
    List<Novel> findByAuthor(@Param("author") String author);
    
    /**
     * 根据分类查询
     */
    List<Novel> findByCategory(@Param("category") String category);
}
