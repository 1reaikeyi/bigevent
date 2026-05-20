package title.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import title.common.constant.JwtConstant;
import title.common.util.ThreadLocalContextHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * MyBatis Plus自动填充处理器 - 自动填充创建时间、更新时间、创建人、更新人
 */
@Component
public class AutoMetaObjectHandler implements MetaObjectHandler {
    private Long getUserId(){
        Map<String,Object> claims = ThreadLocalContextHolder.get();
        if(claims == null){
            return 0L;
        }
        String currentId = claims.get(JwtConstant.ID).toString();
        return Long.parseLong(currentId);
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