package com.example.serviceacl.controller;


import com.example.commonutils.entity.AclPermission;
import com.example.commonutils.response.R;
import com.example.serviceacl.service.AclPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限(AclPermission)表控制层
 *
 * @author makejava
 * @since 2020-11-26 13:41:11
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class AclPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private AclPermissionService aclPermissionService;

    @GetMapping
    public R selectAll() {
       List<AclPermission> list =  aclPermissionService.queryAllMenu();
       return R.ok().data(list);
    }

    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        aclPermissionService.removeChildById(id);
        return R.ok();
    }

    /**
     * 给角色分配权限
     * @param roleId roleId
     * @param permissionId permissionId
     * @return
     */
    @PostMapping("/doAssign")
    public R doAssign(String roleId,String[] permissionId) {
        aclPermissionService.saveRolePermissionRealtionShip(roleId,permissionId);
        return R.ok();
    }
}
