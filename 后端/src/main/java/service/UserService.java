package service;

import com.baomidou.mybatisplus.extension.service.IService;
import pojo.Result;
import pojo.User;

public interface UserService extends IService<User> {
    User findByUsername(String username);
    User matchUser(String userName, String password);
    User matchEmail(String email);
}
