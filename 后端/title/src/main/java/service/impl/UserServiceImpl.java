package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pojo.Result;
import service.UserService;
import repository.UserMapper;
import pojo.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import web.utils.ThreadLocalContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
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

    @Override
    public User matchEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email);
        User checkUser = this.getOne(queryWrapper);
        if (checkUser == null) {
            throw new RuntimeException("不存在用户");
        }
        return checkUser;
    }

}
