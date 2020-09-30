package com.example.serviceoss;

import org.junit.Test;

/**
 * create by Freedom on 2020/9/17
 */
public class OssTest {
    String endpoint = "oss-cn-beijing.aliyuncs.com";
    @Test
    public void strSub(){
        String s = "https://freedom98.oss-cn-beijing.aliyuncs.com/2020/09/09/311cf2f7ef6645d5a1e3dbb0bb001d81default.jpg";
        int i = s.lastIndexOf(endpoint);
        String substring = s.substring(i+endpoint.length()+1);
        System.out.println("substring = " + substring);
    }
}
