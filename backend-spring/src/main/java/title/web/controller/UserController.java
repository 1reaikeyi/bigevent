package title.web.controller;

import title.common.constant.JwtConstant;
import title.common.properties.JwtProperties;
import title.common.result.Result;
import title.common.util.JwtUtil;
import title.common.util.ThreadLocalContextHolder;
import title.domain.entity.User;
import title.service.UserService;
import title.web.annotation.Info;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
@Slf4j
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
    public Result register(@RequestBody User user){
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
    public Result login(String userName, @Pattern(regexp = "^\\S{5,16}$") String password){
        User user = userService.matchUser(userName, password);
        Map<String,Object> map = new HashMap<>();
        map.put(JwtConstant.ID, user.getId());
        map.put(JwtConstant.NAME, user.getUserName());
        ThreadLocalContextHolder.set(map);
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtlMillis(), map);
        stringRedisTemplate.opsForValue().set("token:"+user.getId(), token, jwtProperties.getTtlMillis(), TimeUnit.SECONDS);
        return Result.success(token);
    }

    /**
     * 发送验证码
     *
     * @param email 邮箱地址
     * @return 结果
     */
    @Info(desc = "发送验证码")
    @PostMapping("/code")
    public Result sendCode(@Email String email) {
        String secret = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(secret.length());
            codeBuilder.append(secret.charAt(index));
        }
        String code = codeBuilder.toString();
        stringRedisTemplate.opsForValue().set("code:"+email, code, 10, TimeUnit.MINUTES);
        return Result.success("验证码："+code);
    }

    /**
     * 邮箱登录
     *
     * @param email 邮箱地址
     * @param code 验证码
     * @return 结果
     */
    @Info(desc = "邮箱登录")
    @PostMapping("byEmail")
    public Result loginByEmail(String email, String code){
        String standard_code = stringRedisTemplate.opsForValue().get("code:"+email);
        if(standard_code == null){
            return Result.error("验证码获取失败");
        }
        User user = userService.matchEmail(email);
        if(standard_code.equals(code)){
            Map<String,Object> map = new HashMap<>();
            map.put(JwtConstant.ID, user.getId());
            map.put(JwtConstant.NAME, user.getUserName());
            ThreadLocalContextHolder.set(map);
            String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtlMillis(), map);
            stringRedisTemplate.opsForValue().set("token:"+ user.getId(), token, jwtProperties.getTtlMillis(), TimeUnit.SECONDS);
            return Result.success(token);
        }
        return Result.error("验证失败");
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
    public Result updateUser(@RequestBody User user){
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
        stringRedisTemplate.delete("token:"+ user.getId());
        return Result.success("更新密码成功::"+user.getId());
    }
}