package web.interceptors;

import common.JwtConstant;
import common.JwtProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import web.utils.JwtUtil;
import web.utils.ThreadLocalContextHolder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        // 获取token,并验证token是否有效
        String token = request.getHeader("Authorization");
        try {
            if (token == null) {
                // 没有登录，返回错误信息
                response.setStatus(401);
                return false;
            }
            String standard_token = stringRedisTemplate.opsForValue().get("token:"+ JwtConstant.ID);
            if (!standard_token.equals(token)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            // 没有登录，返回错误信息
            response.setStatus(401);
            return false;
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            ThreadLocalContextHolder.remove();
    }
}
