package com.example.serviceacl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.AclRole;

import java.util.List;

/**
 * (AclRole)表服务接口
 *
 * @author makejava
 * @since 2020-11-26 13:41:13
 */
public interface AclRoleService extends IService<AclRole> {

    List<AclRole> selectRoleByUserId(String id);

}
