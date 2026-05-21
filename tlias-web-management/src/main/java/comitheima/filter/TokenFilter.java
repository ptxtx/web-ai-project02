package comitheima.filter;

import comitheima.Utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter("/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("拦截到了请求。。。");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //1.获取到请求路径
        String requestURI = req.getRequestURI();
        //2.判断是否是登录请求，如果是登录操作则直接放行（因为此时还没有令牌）
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            chain.doFilter(request,response);
            return;
        }
        //3.获取请求头中的token
        String token = req.getHeader("token");
        //4.判断token是否存在 如果不存在，返回错误信息（响应401状态码）
        if(token==null||token.isEmpty()){
            log.info("令牌为空，响应401");
            resp.setStatus(401);//给前端响应401状态码
            return;
        }
        //5.令牌存在，则解析令牌（校验），如果解析失败，返回错误信息（响应401状态码）
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            resp.setStatus(401);
            return;
        }
        //6.校验通过则放行
        log.info("令牌解析成功，放行");
        chain.doFilter(request,response);



    }
}
