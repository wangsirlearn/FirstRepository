package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.popj.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
    全局异常处理器：捕获所有异常
 */
@Slf4j
@RestControllerAdvice // 全局异常处理器
// @RestControllerAdvice = @ControllerAdvice + @ResponseBody
public class GlobalException {
    @ExceptionHandler //捕获异常
    public Result handleException(Exception e){
        log.error("服务器发生异常：{}",e.getMessage());

        return Result.error("服务器发生异常"); // 服务器发生异常 会发到前端页面上
    }
    // 具体的异常处理 捕获到出错的信息
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("服务器发生异常：{}",e.getMessage());
        String message= e.getMessage();
        int i=message.indexOf("Duplicate entry");
        String errMessage=message.substring(i);
        String [] Message=message.split(" ");
        log.error("错误信息：{}",Message[2]);
        return Result.error(Message[2]); // 错误信息会发到前端页面上
    }

}
