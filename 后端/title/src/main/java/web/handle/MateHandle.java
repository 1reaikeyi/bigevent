package web.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import common.JwtConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import web.utils.ThreadLocalContextHolder;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class MateHandle implements MetaObjectHandler {
    private Long getUserId(){
        Map<String,Object> claims = ThreadLocalContextHolder.get();
        if(claims==null){
            return 0L;
        }
        String currentId = claims.get(JwtConstant.ID).toString();
        Long id = Long.parseLong(currentId);
        return id;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createUser", getUserId(), metaObject);
        this.setFieldValByName("updateUser", getUserId(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateUser", getUserId(), metaObject);
    }
}
