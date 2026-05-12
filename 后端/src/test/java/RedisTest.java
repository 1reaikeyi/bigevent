import common.JwtConstant;
import common.JwtProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import web.TitleApplication;
import web.utils.JwtUtil;
import web.utils.ThreadLocalContextHolder;

import java.util.Map;

@SpringBootTest(classes = TitleApplication.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        String standard_token = stringRedisTemplate.opsForValue().get("token:"+ 1);
        System.out.println(standard_token);
    }

}
