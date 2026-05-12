package web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import common.JwtConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import repository.CategoryMapper;
import pojo.Article;
import pojo.Category;
import pojo.Result;
import service.ArtitleService;
import service.CategoryService;
import web.utils.ThreadLocalContextHolder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 文章管理控制器
 * <p>
 * 负责文章的增删改查、状态管理、分类关联等核心功能，
 * 提供完整的文章管理API接口，支持JWT认证和权限控制。
 * </p>
 * @author Smart-doc
 * @since 1.0.0
 * @version 1.0.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArtitleService artitleService;
    
    /**
     * 添加文章
     * 
     * @param article 文章信息
     * @return 结果
     */
    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article) {
        artitleService.save(article);
        return Result.success("@PostMapping::"+article.getId());
    }
    
    /**
     * 获取所有文章
     * 
     * @return 结果
     */
    @GetMapping("/list")
    public Result readArticle() {
        return Result.success(artitleService.list());
    }

    /**
     * 获取分页文章列表
     *
     * @param pageNum 页码
     * @param pageSize 每页数量，
     * @return 分页结果包含文章列表
     */
    @GetMapping
    public Result getArticlePage(int pageNum, int pageSize){
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getState,"已发布");
        wrapper.orderByDesc(Article::getCreateTime);
        IPage<Article> page = new Page<>(pageNum, pageSize);
        IPage<Article> resultPage = artitleService.page(page, wrapper);
        return Result.success(resultPage);
    }
    
    /**
     * 获取单个文章
     * 
     * @param id 文章ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(artitleService.getById(id));
    }
    
    /**
     * 更新文章
     * 
     * @param article 文章信息
     * @return 结果
     */
    @PutMapping
    public Result updateArticle(@RequestBody @Validated Article article) {
        artitleService.updateById(article);
        return Result.success("@PutMapping::"+article.getId());
    }
    
    /**
     * 删除文章
     * 
     * @param id 文章ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        artitleService.removeById(id);
        return Result.success("DeleteMapping::"+id);
    }
}
