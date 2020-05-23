package com.atguigu.servicebase.handler;

import com.atguigu.commonresult.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("哎呀，系统开小差啦，请稍后重试~~");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result error(NullPointerException e) {
        e.printStackTrace();
        return Result.error().message("哎呀，空指针错误啦~~");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error().status(e.getCode()).message(e.getMsg());
    }
}
