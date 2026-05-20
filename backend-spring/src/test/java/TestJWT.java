import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import controller.TitleApplication;

@SpringBootTest(classes = TitleApplication.class)
public class TestJWT {
    @Test
    public void testJWT() {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", "1");
//        claims.put("email", "admin@test.com");
//        String token = JwtUtil.genToken(claims);
//        System.out.println(token);
    }
    @Test
    public void testJWT2() {
//        The string '"alg":"HS256","typ":"JWT"}' doesn't have a valid JSON format.
//        The string '"claims":{"id":"1","email":"admin@test.com"},"exp":1773535156}' doesn't have a valid JSON format.
//        The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256
//        eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOiIxIiwiZW1haWwiOiJhZG1pbkB0ZXN0LmNvbSJ9LCJleHAiOjE3NzM1MzUxNTZ9.9aY-p7tzzWQj-DbQkdPl62jnKV18N2XytuI-FsOcqDo
//        Map<String, Object> claims = JwtUtil.parseToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
//                "eyJjbGFpbXMiOnsiaWQiOiIxIiwiZW1haWwiOiJhZG1pbkB0ZXN0LmNvbSJ9LCJleHAiOjE3NzM1MzUxNTZ9." +
//                "9aY-p7tzzWQj-DbQkdPl62jnKV18N2XytuI-FsOcqDo");
//        System.out.println(claims);
    }
}
