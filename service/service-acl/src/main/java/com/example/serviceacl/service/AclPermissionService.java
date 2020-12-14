package com.example.serviceacl.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.AclPermission;

import java.util.List;

/**
 * 权限(AclPermission)表服务接口
 *
 * @author makejava
 * @since 2020-11-26 13:41:09
 */
public interface AclPermissionService extends IService<AclPermission> {

    List<AclPermission> queryAllMenu();

    void removeChildById(String id);

    void saveRolePermissionRealtionShip(String roleId, String[] permissionId);

    List<String> selectPermissionValueByUserId(String userId);

    List<JSONObject> getMenu(String username);

    List<JSONObject> selectPermissionByUserId(String id);
}
