import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import controller.TitleApplication;

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
