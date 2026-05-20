package title.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * BigEvent启动类 - Spring Boot应用入口
 */
@SpringBootApplication(scanBasePackages = {"title.web","title.service","title.common"})
@MapperScan("title.mapper")
@Slf4j
public class BigEventApplication {
    public static void main(String[] args) {
        SpringApplication.run(BigEventApplication.class, args);
        log.info(">>>>>>BigEvent启动成功");
    }
}