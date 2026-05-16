package title.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import title.domain.entity.User;

/**
 * 用户Mapper接口 - 提供用户数据访问操作
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}