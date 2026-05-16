package title.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import title.domain.entity.Category;

/**
 * 分类Mapper接口 - 提供分类数据访问操作
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
}