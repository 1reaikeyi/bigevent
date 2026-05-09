package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import web.interceptors.LoginInterceptor;
import web.interceptors.ReLoginInterceptor;


@Configuration
public class WebConfigConfiguration implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private ReLoginInterceptor reLoginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器，排除注册和登录接口
        registry.addInterceptor(reLoginInterceptor)
                .addPathPatterns("/user/**").order(0);
       registry.addInterceptor(loginInterceptor)
               .addPathPatterns("/user/**")
               .excludePathPatterns("/user/register", "/user/login", "/user/logout",
                       "/user/code","/user/byEmail");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/云/**")
                .addResourceLocations("file:云/");
    }

}
