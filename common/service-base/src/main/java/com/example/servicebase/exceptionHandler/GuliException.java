package com.example.servicebase.exceptionHandler;

import com.example.commonutils.response.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * create by Freedom on 2020/8/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor   //生成无参数构造
public class GuliException extends RuntimeException {
    private Boolean success;
    private Integer code;//状态码
    private String message;//异常信息

    public GuliException(ResultCodeEnum resultCodeEnum) {
        this.success = resultCodeEnum.getSuccess();
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public GuliException(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
