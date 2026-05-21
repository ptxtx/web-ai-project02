package comitheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
//@WebFilter(urlPatterns = "/*")//拦截所有请求
@Slf4j
public class DemoFilter implements Filter {
    //初始化，在web服务器启动时执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法。。。");
    }

    @Override
    public void destroy() {
        log.info("destroy 销毁方法。。。。");
    }

    @Override//拦截到请求后执行，执行多次
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("拦截到了请求。。。放行前");
        //放行
        chain.doFilter(request,response);//放行到下一个过滤器 如果没有下一个过滤器就是目标资源

        log.info("拦截到了请求。。。放行后");


    }
}
