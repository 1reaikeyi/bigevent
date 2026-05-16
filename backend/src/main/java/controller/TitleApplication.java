package controller;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"common", "mapper", "service", "controller"})
@MapperScan("mapper")
@Slf4j
public class TitleApplication {
    public static void main(String[] args) {
        SpringApplication.run(TitleApplication.class, args);
        log.info(">>>>>>Title启动成功");
    }
}