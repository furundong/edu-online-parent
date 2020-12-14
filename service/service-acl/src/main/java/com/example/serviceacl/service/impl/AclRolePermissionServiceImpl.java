package com.example.serviceacl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.serviceacl.dao.AclRolePermissionDao;
import com.example.commonutils.entity.AclRolePermission;
import com.example.serviceacl.service.AclRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色权限(AclRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2020-11-26 13:41:17
 */
@Service("aclRolePermissionService")
public class AclRolePermissionServiceImpl extends ServiceImpl<AclRolePermissionDao, AclRolePermission> implements AclRolePermissionService {

}
