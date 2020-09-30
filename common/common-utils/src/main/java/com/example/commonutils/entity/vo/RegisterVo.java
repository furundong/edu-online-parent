package com.example.commonutils.entity.vo;

import lombok.Data;

/**
 * create by Freedom on 2020/9/25
 */
@Data
//(value="注册对象", description="注册对象")
public class RegisterVo {

    //(value = "昵称")
    private String nickname;

    //(value = "手机号")
    private String mobile;

    //(value = "密码")
    private String password;

    //(value = "验证码")
    private String code;
}
