package web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = {"common", "repository", "service", "web"})
@MapperScan("repository")
@Slf4j
public class TitleApplication {
    public static void main(String[] args) {
        SpringApplication.run(TitleApplication.class, args);
        log.info(">>>>>>Title启动成功");
    }
}