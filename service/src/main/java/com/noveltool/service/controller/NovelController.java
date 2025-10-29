package com.noveltool.service.controller;

import com.noveltool.common.dto.DataResponse;
import com.noveltool.common.dto.NovelDTO;
import com.noveltool.api.service.INovelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小说控制器
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@RestController
@RequestMapping("/novels")
@RequiredArgsConstructor
public class NovelController {
    
    private final INovelService novelService;
    
    /**
     * 获取所有小说
     */
    @GetMapping
    public DataResponse<List<NovelDTO>> getAllNovels() {
        List<NovelDTO> novels = novelService.getAllNovels();
        return DataResponse.success(novels);
    }
    
    /**
     * 根据ID获取小说
     */
    @GetMapping("/{id}")
    public DataResponse<NovelDTO> getNovelById(@PathVariable Long id) {
        NovelDTO novel = novelService.getNovelById(id);
        return DataResponse.success(novel);
    }
    
    /**
     * 创建小说
     */
    @PostMapping
    public DataResponse<NovelDTO> createNovel(@Valid @RequestBody NovelDTO novelDTO) {
        NovelDTO createdNovel = novelService.createNovel(novelDTO);
        return DataResponse.success("小说创建成功", createdNovel);
    }
    
    /**
     * 更新小说
     */
    @PutMapping("/{id}")
    public DataResponse<NovelDTO> updateNovel(
            @PathVariable Long id,
            @Valid @RequestBody NovelDTO novelDTO) {
        NovelDTO updatedNovel = novelService.updateNovel(id, novelDTO);
        return DataResponse.success("小说更新成功", updatedNovel);
    }
    
    /**
     * 删除小说
     */
    @DeleteMapping("/{id}")
    public DataResponse<Void> deleteNovel(@PathVariable Long id) {
        novelService.deleteNovel(id);
        return DataResponse.success("小说删除成功");
    }
    
    /**
     * 根据标题搜索小说
     */
    @GetMapping("/search")
    public DataResponse<List<NovelDTO>> searchByTitle(@RequestParam String title) {
        List<NovelDTO> novels = novelService.searchByTitle(title);
        return DataResponse.success(novels);
    }
    
    /**
     * 根据作者查找小说
     */
    @GetMapping("/author/{author}")
    public DataResponse<List<NovelDTO>> findByAuthor(@PathVariable String author) {
        List<NovelDTO> novels = novelService.findByAuthor(author);
        return DataResponse.success(novels);
    }
    
    /**
     * 根据分类查找小说
     */
    @GetMapping("/category/{category}")
    public DataResponse<List<NovelDTO>> findByCategory(@PathVariable String category) {
        List<NovelDTO> novels = novelService.findByCategory(category);
        return DataResponse.success(novels);
    }
}
