package service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import entity.Article;
import mapper.ArtitleMapper;
import service.ArtitleService;

@Service
public class ArtitleServiceImpl extends ServiceImpl<ArtitleMapper, Article> implements ArtitleService {

}
