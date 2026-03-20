package org.example.config;

import org.example.interceptor.DomeInterceptor;
import org.example.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration // 配置类 声明是配置类
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private DomeInterceptor domeInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册拦截器 并添加拦截路径
//        //registry.addInterceptor(new DomeInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(domeInterceptor).addPathPatterns("/**");
//    }



        @Autowired
        private TokenInterceptor tokenInterceptor;
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(tokenInterceptor)
                    .addPathPatterns("/**") // 拦截所有请求 配置拦截路径
                    .excludePathPatterns("/login"); // 放行登录接口
        }
}
