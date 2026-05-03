package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import service.CategoryService;
import repository.CategoryMapper;
import pojo.Category;
import web.utils.ThreadLocalContextHolder;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
