package com.example.serviceacl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.serviceacl.dao.AclUserRoleDao;
import com.example.commonutils.entity.AclUserRole;
import com.example.serviceacl.service.AclUserRoleService;
import org.springframework.stereotype.Service;

/**
 * (AclUserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-11-26 13:41:18
 */
@Service("aclUserRoleService")
public class AclUserRoleServiceImpl extends ServiceImpl<AclUserRoleDao, AclUserRole> implements AclUserRoleService {

}
