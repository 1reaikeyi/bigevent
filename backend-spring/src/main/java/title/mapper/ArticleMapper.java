package title.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import title.domain.entity.Article;

/**
 * 文章Mapper接口 - 提供文章数据访问操作
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
}