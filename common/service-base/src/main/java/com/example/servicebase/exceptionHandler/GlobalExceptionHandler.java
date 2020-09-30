package com.example.servicebase.exceptionHandler;

import com.example.commonutils.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by Freedom on 2020/8/13
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //自定义异常
    @ExceptionHandler(value = GuliException.class)
    @ResponseBody //为了返回数据
    public R error(GuliException e) {
        log.error(e.getMessage());
        System.out.println("e = " + e);
        return R.error().code(e.getCode()).success(e.getSuccess()).message(e.getMessage());
    }


    //特定异常
    @ExceptionHandler(value = ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理..");
    }


    //指定出现什么异常执行这个方法
    @ExceptionHandler(value = Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理...");
    }
}

