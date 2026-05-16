package title.web.controller;

import title.common.result.Result;
import title.domain.entity.Category;
import title.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 分类管理控制器
 * 
 * @author Smart-doc
 * @since 1.0.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    /**
     * 添加分类
     * 
     * @param category 分类信息
     * @return 结果
     */
    @PostMapping
    public Result addCategory(@RequestBody @Validated Category category) {
        categoryService.save(category);
        return Result.success("@PostMapping::"+category.getId());
    }
    
    /**
     * 获取所有分类
     * 
     * @return 结果
     */
    @GetMapping
    public Result readCategory() {
        return Result.success(categoryService.list());
    }

    /**
     * 获取分类详情
     * 
     * @param id 分类ID
     * @return 分类详情信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(categoryService.getById(id));
    }
    
    /**
     * 更新分类
     * 
     * @param category 分类信息
     * @return 结果
     */
    @PutMapping
    public Result updateCategory(@RequestBody @Validated Category category) {
        categoryService.updateById(category);
        return Result.success("@PutMapping::"+category.getId());
    }
    
    /**
     * 删除分类
     * 
     * @param id 分类ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        categoryService.removeById(id);
        return Result.success("@DeleteMapping::"+id);
    }
}