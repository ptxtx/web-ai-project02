package comitheima.Config;

import comitheima.intercepter.DemoIntercepter;
import comitheima.intercepter.TokenIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//注册拦截器之前要先定义一个配置类
@Configuration//标识这个类是配置类（底层封装了Component）
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    //private DemoIntercepter demoIntercepter;
    private TokenIntercepter tokenIntercepter;
    @Override//添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenIntercepter).addPathPatterns("/**");
    }
}
