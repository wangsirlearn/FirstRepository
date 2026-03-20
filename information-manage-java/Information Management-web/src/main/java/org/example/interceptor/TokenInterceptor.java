package org.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.Utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器
 */
@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getRequestURI();
        if (path.contains("/login")){
            return true;
        }else {
            String token = request.getHeader("token"); //获取请求头中的token
            if (token == null|| token.isEmpty()){
                log.info("请求头中没有token");
                response.setStatus(401);
                return false;
            } else {
                try {
                    JWTUtils.parseJWT(token); //解析令牌 若不报错就是合法的 不需要自己对比
                    // 靠的是Signature
                } catch (Exception e){
                    response.setStatus(401);
                    return false;
                }

            }
        }
        log.debug("合法");
        return true;
    }
}
