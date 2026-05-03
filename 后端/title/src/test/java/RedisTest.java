import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import web.TitleApplication;

@SpringBootTest(classes = TitleApplication.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test() {
        ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
        operations.set("hello", "world");
    }
    @Test
    public void test2() {
        ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
        System.out.println(operations.get("hello"));
    }
}
