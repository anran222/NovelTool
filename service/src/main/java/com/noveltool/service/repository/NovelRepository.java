package com.noveltool.service.repository;

import com.noveltool.common.entity.Novel;
import com.noveltool.service.mapper.NovelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 小说数据仓库类
 * 封装Mapper操作，提供更高级的数据访问接口
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Repository
@RequiredArgsConstructor
public class NovelRepository {
    
    private final NovelMapper novelMapper;
    
    /**
     * 查询所有小说
     */
    public List<Novel> findAll() {
        return novelMapper.findAll();
    }
    
    /**
     * 根据ID查询小说
     */
    public Optional<Novel> findById(Long id) {
        Novel novel = novelMapper.findById(id);
        return Optional.ofNullable(novel);
    }
    
    /**
     * 保存小说（新增或更新）
     */
    public Novel save(Novel novel) {
        if (novel.getId() == null) {
            // 新增
            novelMapper.insert(novel);
            return novel;
        } else {
            // 更新
            novelMapper.update(novel);
            return findById(novel.getId()).orElse(novel);
        }
    }
    
    /**
     * 新增小说
     */
    public Novel insert(Novel novel) {
        novelMapper.insert(novel);
        return novel;
    }
    
    /**
     * 更新小说
     */
    public void update(Novel novel) {
        novelMapper.update(novel);
    }
    
    /**
     * 根据ID删除小说
     */
    public void deleteById(Long id) {
        novelMapper.deleteById(id);
    }
    
    /**
     * 检查小说是否存在
     */
    public boolean existsById(Long id) {
        return novelMapper.existsById(id);
    }
    
    /**
     * 根据标题模糊查询
     */
    public List<Novel> findByTitleContaining(String title) {
        return novelMapper.findByTitleContaining(title);
    }
    
    /**
     * 根据作者查询
     */
    public List<Novel> findByAuthor(String author) {
        return novelMapper.findByAuthor(author);
    }
    
    /**
     * 根据分类查询
     */
    public List<Novel> findByCategory(String category) {
        return novelMapper.findByCategory(category);
    }
}

