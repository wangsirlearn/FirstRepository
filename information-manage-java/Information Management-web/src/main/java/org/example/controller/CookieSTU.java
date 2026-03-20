package org.example.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.popj.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@Slf4j

public class CookieSTU {

    @GetMapping("/setCookie")
    //设置 Cookie
    public Result setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("cookie_my","wys111");
        response.addCookie(cookie);
        return Result.success();

    }
    @GetMapping("/getCookie")
    //获取 Cookie
    public Result getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cookie_my")) {
                return Result.success(cookie.getValue());

            }
        }
        return Result.success();
    }

    @GetMapping("/setSession")
    //存储 Session 值
    public Result setSession(HttpSession  session) {
        session.setAttribute("session_my","wys2222");
        // session存储的值是wys2222
        return Result.success();
    }
    @GetMapping("/getSession")
    //获取 Session
    public Result getSession(HttpSession session) {
        Object session_my = session.getAttribute("session_my");
        log.debug("session_my={}",session_my);
        return Result.success();
    }

}
