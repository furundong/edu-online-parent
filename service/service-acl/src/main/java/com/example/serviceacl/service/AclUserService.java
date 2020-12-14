package com.example.serviceacl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.AclUser;

import java.util.Map;

/**
 * 用户表(AclUser)表服务接口
 *
 * @author makejava
 * @since 2020-11-26 13:41:18
 */
public interface AclUserService extends IService<AclUser> {

    AclUser selectByUsername(String username);

    Map<String, Object> getUserInfo(String username);
}
