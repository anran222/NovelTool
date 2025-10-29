package com.noveltool.api.service;

import com.noveltool.common.dto.NovelDTO;

import java.util.List;

/**
 * 小说服务接口
 * 
 * @author NovelTool
 * @since 1.0.0
 */
public interface INovelService {
    
    /**
     * 获取所有小说
     */
    List<NovelDTO> getAllNovels();
    
    /**
     * 根据ID获取小说
     */
    NovelDTO getNovelById(Long id);
    
    /**
     * 创建小说
     */
    NovelDTO createNovel(NovelDTO novelDTO);
    
    /**
     * 更新小说
     */
    NovelDTO updateNovel(Long id, NovelDTO novelDTO);
    
    /**
     * 删除小说
     */
    void deleteNovel(Long id);
    
    /**
     * 根据标题搜索小说
     */
    List<NovelDTO> searchByTitle(String title);
    
    /**
     * 根据作者查找小说
     */
    List<NovelDTO> findByAuthor(String author);
    
    /**
     * 根据分类查找小说
     */
    List<NovelDTO> findByCategory(String category);
}

