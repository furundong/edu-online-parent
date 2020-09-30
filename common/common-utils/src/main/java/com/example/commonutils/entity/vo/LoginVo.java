package com.example.commonutils.entity.vo;

import lombok.Data;

/**
 * create by Freedom on 2020/9/25
 */
@Data
//(value="登录对象", description="登录对象")
public class LoginVo {

    //(value = "手机号")
    private String mobile;

    //(value = "密码")
    private String password;
}
