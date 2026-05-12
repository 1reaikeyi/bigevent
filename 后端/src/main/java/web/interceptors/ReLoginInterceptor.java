package web.interceptors;

import common.JwtConstant;
import common.JwtProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import web.utils.JwtUtil;
import web.utils.ThreadLocalContextHolder;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Component
public class ReLoginInterceptor implements HandlerInterceptor {
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
             Map<String, Object> claims =  JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
             String currentId = claims.get(JwtConstant.ID).toString();
             Long id = Long.parseLong(currentId);
             String standard_token = stringRedisTemplate.opsForValue().get("token:"+ id);

             if (!standard_token.equals(token)) {
                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                 return false;
             }
             // 刷新 Redis 中 token 的过期时间（滑动过期策略）
             stringRedisTemplate.expire("token:" + id, jwtProperties.getTtlMillis(), TimeUnit.SECONDS);
             //将用户id设置到ThreadContext中
             ThreadLocalContextHolder.set(claims);
             return true;
         } catch (Exception e) {
             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             response.getWriter().write(e.getMessage());
             return false;
         }
     }
     @Override
     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         ThreadLocalContextHolder.remove();
     }
}
