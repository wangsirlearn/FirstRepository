package org.example.filter;


import jakarta.servlet.*;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.Utils.JWTUtils;


import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)  servletResponse;
        String path = request.getRequestURI();
        if (path.contains("/login")){
            filterChain.doFilter(request,response);
            return;
        }else {
            String token = request.getHeader("token"); //获取请求头中的token
            if (token == null|| token.isEmpty()){
                log.info("请求头中没有token");
                response.setStatus(401);
                return;
            } else {
                try {
                    JWTUtils.parseJWT(token); //解析令牌 若不报错就是合法的 不需要自己对比
                    // 靠的是Signature
                } catch (Exception e){
                    response.setStatus(401);
                    return;
                }

            }
        }
        log.debug("合法");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
