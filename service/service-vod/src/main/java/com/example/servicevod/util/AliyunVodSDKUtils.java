package com.example.servicevod.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * create by Freedom on 2020/9/10
 */
public class AliyunVodSDKUtils {

    /**
     * 初始化
     * @param accessKeyId accessKeyId
     * @param accessKeySecret accessKeySecret
     * @return DefaultAcsClient
     */
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }
}
