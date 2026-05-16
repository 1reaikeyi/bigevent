package title.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import title.domain.entity.Category;
import title.mapper.CategoryMapper;
import title.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * 分类服务实现类 - 实现分类相关业务逻辑
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}