package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import service.UserService;
import repository.UserMapper;
import pojo.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import web.utils.ThreadLocalContextHolder;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findByUsername(String username) {
        return this.lambdaQuery().eq(User::getUserName, username)
                .one();
    }

    @Override
    public User matchUser(String userName, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName)
                .eq(User::getPassword, password);
        User checkUser = this.getOne(queryWrapper);
        if (checkUser == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        return checkUser;
    }

}
