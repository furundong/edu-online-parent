package com.example.serviceacl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.AclPermission;
import com.example.commonutils.entity.AclRolePermission;
import com.example.commonutils.entity.AclUser;
import com.example.serviceacl.dao.AclPermissionDao;
import com.example.serviceacl.helper.MemuHelper;
import com.example.serviceacl.helper.PermissionHelper;
import com.example.serviceacl.service.AclPermissionService;
import com.example.serviceacl.service.AclRolePermissionService;
import com.example.serviceacl.service.AclUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.example.serviceacl.helper.PermissionHelper.bulid;

/**
 * 权限(AclPermission)表服务实现类
 *
 * @author makejava
 * @since 2020-11-26 13:41:10
 */
@Service("aclPermissionService")
public class AclPermissionServiceImpl extends ServiceImpl<AclPermissionDao, AclPermission> implements AclPermissionService {

    @Resource
    AclRolePermissionService aclRolePermissionService;

    @Resource
    AclUserService aclUserService;

    @Resource
    AclPermissionService aclPermissionService;

    @Override
    public List<AclPermission> queryAllMenu() {
        QueryWrapper<AclPermission> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<AclPermission> permissionList = baseMapper.selectList(wrapper);

        return bulid(permissionList);
    }

    @Override
    public void removeChildById(String id) {
        List<String> idList = new ArrayList<>();
        this.selectChildListById(id, idList);
        //把根据节点id放到list中
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public void saveRolePermissionRealtionShip(String roleId, String[] permissionIds) {
        aclRolePermissionService.remove(new QueryWrapper<AclRolePermission>().eq("role_id", roleId));

        List<AclRolePermission> rolePermissionList = new ArrayList<>();
        for(String permissionId : permissionIds) {
            if(StringUtils.isEmpty(permissionId)) continue;
            AclRolePermission rolePermission = new AclRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        aclRolePermissionService.saveBatch(rolePermissionList);
    }

    @Override
    public List<String> selectPermissionValueByUserId(String userId) {

        List<String> selectPermissionValueList;
        if(this.isSysAdmin(userId)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(userId);
        }
        return selectPermissionValueList;
    }

    @Override
    public List<JSONObject> getMenu(String username) {
        AclUser user = aclUserService.selectByUsername(username);

        //根据用户id获取用户菜单权限
        List<JSONObject> permissionList = aclPermissionService.selectPermissionByUserId(user.getId());
        return permissionList;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String userId) {
        List<AclPermission> selectPermissionList = null;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectList(null);
        } else {
            selectPermissionList = baseMapper.selectPermissionByUserId(userId);
        }

        List<AclPermission> permissionList = PermissionHelper.bulid(selectPermissionList);
        return MemuHelper.bulid(permissionList);
    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        AclUser user = aclUserService.getById(userId);

        if(null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }

    /**
     *	递归获取子节点
     * @param id
     * @param idList
     */
    private void selectChildListById(String id, List<String> idList) {
        List<AclPermission> childList = baseMapper.selectList(new QueryWrapper<AclPermission>().eq("pid", id).select("id"));
        childList.forEach(item -> {
            idList.add(item.getId());
            this.selectChildListById(item.getId(), idList);
        });
    }
}
