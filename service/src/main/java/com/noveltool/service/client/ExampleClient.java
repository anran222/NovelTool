package com.noveltool.service.client;

import com.noveltool.common.dto.ClientResponse;
import com.noveltool.common.dto.NovelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 示例外部接口Feign客户端
 * 支持各种参数传递方式和下划线转驼峰
 * 统一使用ClientResponse包装返回结果
 * 
 * @author NovelTool
 * @since 1.0.0
 */
@FeignClient(
    name = "exampleClient",
    url = "${external.api.base-url:http://localhost:8080}",
    configuration = com.noveltool.service.client.config.FeignConfig.class
)
public interface ExampleClient {
    
    /**
     * GET请求 - 使用@RequestParam传递参数
     */
    @GetMapping("/api/data")
    ClientResponse<Map<String, Object>> getData(@RequestParam("param_name") String paramName);
    
    /**
     * GET请求 - 使用@PathVariable传递路径参数
     */
    @GetMapping("/api/data/{id}")
    ClientResponse<Map<String, Object>> getDataById(@PathVariable("id") Long id);
    
    /**
     * GET请求 - 多个查询参数
     */
    @GetMapping("/api/search")
    ClientResponse<List<Map<String, Object>>> search(
        @RequestParam("keyword") String keyword,
        @RequestParam("page_num") Integer pageNum,
        @RequestParam("page_size") Integer pageSize
    );
    
    /**
     * POST请求 - 使用@RequestBody传递对象（自动转换为下划线格式）
     */
    @PostMapping("/api/data")
    ClientResponse<Map<String, Object>> createData(@RequestBody NovelDTO novelDto);
    
    /**
     * POST请求 - 使用@RequestParam传递多个参数
     */
    @PostMapping("/api/data/form")
    ClientResponse<Map<String, Object>> createDataByForm(
        @RequestParam("title") String title,
        @RequestParam("author_name") String authorName,
        @RequestParam("create_time") String createTime
    );
    
    /**
     * PUT请求 - 更新数据
     */
    @PutMapping("/api/data/{id}")
    ClientResponse<Map<String, Object>> updateData(
        @PathVariable("id") Long id,
        @RequestBody NovelDTO novelDto
    );
    
    /**
     * DELETE请求
     */
    @DeleteMapping("/api/data/{id}")
    ClientResponse<Void> deleteData(@PathVariable("id") Long id);
    
    /**
     * POST请求 - 传递Map参数（自动转换为下划线格式）
     */
    @PostMapping("/api/data/map")
    ClientResponse<Map<String, Object>> createDataByMap(@RequestBody Map<String, Object> data);
}
