package com.noveltool.service.service.impl;

import com.noveltool.common.dto.NovelDTO;
import com.noveltool.common.entity.Novel;
import com.noveltool.api.service.INovelService;
import com.noveltool.service.mapper.NovelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 小说服务实现类
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class NovelServiceImpl implements INovelService {
    
    private final NovelMapper novelMapper;
    
    /**
     * 获取所有小说
     */
    @Override
    public List<NovelDTO> getAllNovels() {
        return novelMapper.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * 根据ID获取小说
     */
    @Override
    public NovelDTO getNovelById(Long id) {
        Novel novel = novelMapper.findById(id);
        if (novel == null) {
            throw new RuntimeException("小说不存在，ID: " + id);
        }
        return convertToDTO(novel);
    }
    
    /**
     * 创建小说
     */
    @Override
    @Transactional
    public NovelDTO createNovel(NovelDTO novelDTO) {
        Novel novel = convertToEntity(novelDTO);
        LocalDateTime now = LocalDateTime.now();
        novel.setCreateTime(now);
        novel.setUpdateTime(now);
        
        novelMapper.insert(novel);
        return convertToDTO(novel);
    }
    
    /**
     * 更新小说
     */
    @Override
    @Transactional
    public NovelDTO updateNovel(Long id, NovelDTO novelDTO) {
        Novel existingNovel = novelMapper.findById(id);
        if (existingNovel == null) {
            throw new RuntimeException("小说不存在，ID: " + id);
        }
        
        Novel novel = convertToEntity(novelDTO);
        novel.setId(id);
        novel.setCreateTime(existingNovel.getCreateTime());
        novel.setUpdateTime(LocalDateTime.now());
        
        novelMapper.update(novel);
        return convertToDTO(novelMapper.findById(id));
    }
    
    /**
     * 删除小说
     */
    @Override
    @Transactional
    public void deleteNovel(Long id) {
        if (!novelMapper.existsById(id)) {
            throw new RuntimeException("小说不存在，ID: " + id);
        }
        novelMapper.deleteById(id);
    }
    
    /**
     * 根据标题搜索小说
     */
    @Override
    public List<NovelDTO> searchByTitle(String title) {
        return novelMapper.findByTitleContaining(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * 根据作者查找小说
     */
    @Override
    public List<NovelDTO> findByAuthor(String author) {
        return novelMapper.findByAuthor(author).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * 根据分类查找小说
     */
    @Override
    public List<NovelDTO> findByCategory(String category) {
        return novelMapper.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * 实体转DTO
     */
    private NovelDTO convertToDTO(Novel novel) {
        NovelDTO dto = new NovelDTO();
        dto.setId(novel.getId());
        dto.setTitle(novel.getTitle());
        dto.setContent(novel.getContent());
        dto.setAuthor(novel.getAuthor());
        dto.setCategory(novel.getCategory());
        return dto;
    }
    
    /**
     * DTO转实体
     */
    private Novel convertToEntity(NovelDTO dto) {
        Novel novel = new Novel();
        novel.setId(dto.getId());
        novel.setTitle(dto.getTitle());
        novel.setContent(dto.getContent());
        novel.setAuthor(dto.getAuthor());
        novel.setCategory(dto.getCategory());
        return novel;
    }
}
