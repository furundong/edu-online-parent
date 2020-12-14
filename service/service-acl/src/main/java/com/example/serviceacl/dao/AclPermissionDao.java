package com.example.serviceacl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commonutils.entity.AclPermission;

import java.util.List;

/**
 * 权限(AclPermission)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-26 13:41:06
 */
public interface AclPermissionDao extends BaseMapper<AclPermission> {

    List<String> selectAllPermissionValue();

    List<String> selectPermissionValueByUserId(String userId);

    List<AclPermission> selectPermissionByUserId(String userId);
}
