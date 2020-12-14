package com.example.serviceacl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.AclRole;
import com.example.commonutils.entity.AclUserRole;
import com.example.serviceacl.dao.AclRoleDao;
import com.example.serviceacl.service.AclRoleService;
import com.example.serviceacl.service.AclUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (AclRole)表服务实现类
 *
 * @author makejava
 * @since 2020-11-26 13:41:15
 */
@Service("aclRoleService")
public class AclRoleServiceImpl extends ServiceImpl<AclRoleDao, AclRole> implements AclRoleService {

    @Resource
    AclUserRoleService userRoleService;

    @Override
    public List<AclRole> selectRoleByUserId(String id) {
        //根据用户id拥有的角色id
        List<AclUserRole> userRoleList = userRoleService.list(new QueryWrapper<AclUserRole>().eq("user_id", id).select("role_id"));
        List<String> roleIdList = userRoleList.stream().map(AclUserRole::getRoleId).collect(Collectors.toList());
        List<AclRole> roleList = new ArrayList<>();
        if(roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }
}
