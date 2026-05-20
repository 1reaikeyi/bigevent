package title.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import title.domain.entity.Article;
import title.mapper.ArticleMapper;
import title.service.ArticleService;

/**
 * 文章服务实现类 - 实现文章相关业务逻辑
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}