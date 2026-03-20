package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;



import java.io.IOException;

//@WebFilter(urlPatterns = "/*") // 拦截那些请求 拦截路径的配置
//@WebFilter(urlPatterns = "/login")
//@WebFilter(urlPatterns = "/emps/*") // 拦截目录emps下的的路径
@Slf4j

public class DomeFilter implements Filter {

    //初始化方法 web服务器启动时执行一次
    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("初始化过滤器");
        //做资源环境的准备工作
    }
    //拦截到请求执行 执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("拦截了请求");
        //放行 不放行不会访问到资源，会结束该请求
        //放行后会访问资源，响应请求之后会回来 执行log.debug("放行了请求");
        filterChain.doFilter(servletRequest,servletResponse);
        log.debug("放行了请求");
    }
    // 销毁方法 web服务器停止时执行一次
    @Override
    public void destroy() {
        log.debug("销毁过滤器");
        //做资源环境的清理工作
    }
}
