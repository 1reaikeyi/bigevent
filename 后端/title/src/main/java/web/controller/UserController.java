package web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import common.JwtConstant;
import common.JwtProperties;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojo.Result;
import pojo.User;
import service.UserService;
import web.annotion.Info;
import web.utils.JwtUtil;
import web.utils.ThreadLocalContextHolder;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户管理控制器
 * 
 * @author Smart-doc
 * @since 1.0.0
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * 用户注册
     * 
     * @param user 用户信息
     * @return 结果
     */
    @PostMapping("/register")
    public Result register(@Validated User user){
        User checkUser = userService.findByUsername(user.getUserName());
        if(checkUser != null){
            return Result.error("用户名已存在");
        }
        User newUser = User.builder()
                .userName(user.getUserName()).password(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))
                .nickName(user.getNickName()).email(user.getEmail()).userPic(user.getUserPic())
                .build();
        userService.save(newUser);
        return Result.success("注册成功::"+newUser.getId());
    }
    
    /**
     * 用户登录
     * 
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Info(desc = "用户登录")
    @PostMapping("/login")
    public Result login( String userName, @Pattern(regexp = "^\\S{5,16}$") String password){
        User user = userService.matchUser(userName, password);
        if(user == null){
            return Result.error("用户名或密码错误");
        }
        Map<String,Object> map = new HashMap<>();
        map.put(JwtConstant.ID, user.getId());
        map.put(JwtConstant.NAME, user.getUserName());
        ThreadLocalContextHolder.set(map);
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtlMillis(), map);
        return Result.success(token);
    }

    /**
     * 获取用户信息
     * 
     * @param id 用户ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        return Result.success(userService.getById(id));
       }
       
    /**
     * 更新用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @PutMapping
    public Result updateUser(@RequestBody @Validated User user){
        userService.updateById(user);
        return Result.success("更新成功::"+user.getId());
    }

    /**
     * 更新用户密码
     * 
     * @param params 参数
     * @return 结果
     */
    @PatchMapping("/updatePwd")
    public Result updatePassword(@RequestBody Map<String,String> params){
        String oldPassword = params.get("old_pwd");
        String newPassword = params.get("new_pwd");
        String checkPassword = params.get("check_pwd");
        if(oldPassword == null || newPassword == null || checkPassword == null){
            return Result.error("缺少必要参数");
        }
        if(!newPassword.equals(checkPassword)){
            return Result.error("旧密码与确认密码不一致");
        }
        User user = new User();
        user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        userService.updateById(user);
        return Result.success("更新密码成功::"+user.getId());
    }

}
