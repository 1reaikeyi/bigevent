package title.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import title.domain.entity.User;
import title.mapper.UserMapper;
import title.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户服务实现类 - 实现用户相关业务逻辑
 */
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
        queryWrapper.eq(User::getEmail, email);
        User checkUser = this.getOne(queryWrapper);
        if (checkUser == null) {
            throw new RuntimeException("用户不存在");
        }
        return checkUser;
    }
}