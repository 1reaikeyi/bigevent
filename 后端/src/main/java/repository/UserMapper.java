package repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pojo.User;
@Repository
public interface UserMapper extends BaseMapper<User> {
}
