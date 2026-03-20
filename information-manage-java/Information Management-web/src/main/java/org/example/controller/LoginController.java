package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.popj.Login;
import org.example.popj.Result;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public Result login(@RequestParam() String username,@RequestParam() String password){
        log.debug("用户登录：username={},password={}",username,password);
        Login l= loginService.login(username,password);
        if (l!=null){
            return Result.success(l);
        }else {
            return Result.error("用户名或密码错误");
        }
    }

    /**
     * 登录校验
     * 登陆成功就存储一个登录标记
     * 之后的所有请求都统一拦截 查看登录标记 登录标记存在就放行 登录标记不存在就拦截
     * 统一拦截可使用 过滤器Filter 或 拦截器 interceptor 实现
     * 会话：一次会话中包含多个请求 当用户打开浏览器访问服务器时，会话就会建立，
     *  一方断开连接，会话就会结束
     * 会话跟踪：一种维护浏览器状态的方法，服务端需要识别多次请求的来源，判断
     *  是否是同一个用户，以便在同一会话实现数据共享 比如验证码识别，登录标记
     *
     *  会话跟踪方法：Cookie（客户端跟踪方案,存放在浏览器）、Session(服务器端跟踪方案，存放在服务器)，令牌技术
     *   cookie：Cookie是服务器发送给浏览器的，浏览器保存Cookie，
     *   下次访问时，会发送给服务器， 服务器根据Cookie判断用户 都是自动的，通过cookie响应头和请求头携带cookie
     *   优点：http协议支持的技术 缺点：移动端、移动设备不支持 不安全 cookie不能跨域
     *
     *  session（基于cookie）：Session是服务器保存的是在cookie中存储的session的值，服务器保存Session，下次访问时，会发送给服务器
     *   会生成jsessionid，服务器会根据jsessionid找到session对象，然后获取session对象中的数据
     *      缺点：在集群服务器无法使用session 优点：安全
     *
     *  令牌： 是身份标识 登陆成功后，服务器会生成一个令牌，然后把令牌返回给客户端，客户端下次访问的时候，
     *  会携带令牌，服务器会根据校验令牌
     *  优点：在集群服务器可以使用 服务端不保存信息 支持pc，移动端
     *
     *  JWT（JSON Web Token）令牌：定义了一种简洁的，自包含的格式，用于通信双方以json格式传输数据
     *   组成：Header.Payload.Signature
     *     Header：声明令牌的类型，加密算法 base64编码
     *     Payload：存放有效信息，携带一些自定义信息，默认信息 base64编码
     *     Signature：对header和payload进行签名，防止数据被篡改
     *      会融入Header和Payload，并加入密匙，防止Token被修改
     *    JWT的使用：要引入依赖
     *      得到令牌后，每次请求都会在请求头中携带令牌，服务端会进行验证
     *
     * 令牌的校验:统一拦截请求，对令牌进行校验
     *      统一拦截请求：1，过滤器 2，拦截器
     *
     *      过滤器Filter：在请求处理之前进行执行,拦截请求，
     *        可以做权限校验，日志记录等通用操作
     *      使用步骤：1，定义类，实现Filter接口
     *        2，配置过滤器 @WebFilter(urlPatterns = "/*")
     *           在引导类加@ServletComponentScan 开启扫描，获得Servlet插件支持
     *       过滤器链：过滤器按顺序执行，多个过滤器会按照顺序执行
     *        过滤器顺序：默认按类名排序依次执行 A B C的顺序
     *
     *       拦截器interceptor：在请求处理之前进行执行，拦截请求 Spring自带的
     *       使用步骤：1，定义类，实现HandlerInterceptor接口 @Component
     *        2，注册拦截器,配置类 配置拦截器 @Configuration
     *     若有拦截器和过滤器，请求先被过滤器拦截，再被拦截器拦截
     *
     *     过滤器和拦截器的区别：
     *       1，过滤器是Servlet规范中的组件，拦截器是SpringMVC提供的
     *         所以过滤器会拦截所有的资源，拦截器只会拦截Spring环境中的资源
     *       2，接口规范不同
     */
}
