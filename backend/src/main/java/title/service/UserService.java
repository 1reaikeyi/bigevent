package title.service;

import com.baomidou.mybatisplus.extension.service.IService;
import title.domain.entity.User;

/**
 * 用户服务接口 - 定义用户相关业务操作
 */
public interface UserService extends IService<User> {
    User findByUsername(String username);
    User matchUser(String userName, String password);
    User matchEmail(String email);
}