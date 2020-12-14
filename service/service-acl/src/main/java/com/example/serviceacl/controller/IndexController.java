package com.example.serviceacl.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.commonutils.response.R;
import com.example.serviceacl.service.AclPermissionService;
import com.example.serviceacl.service.AclUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * create by Freedom on 2020/12/1
 */
@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Resource
    private AclUserService aclUserService;

    @Resource
    private AclPermissionService aclPermissionService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public R info() {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = aclUserService.getUserInfo(username);
        return R.ok().data(userInfo);
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @GetMapping("menu")
    public R getMenu() {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = aclPermissionService.getMenu(username);
        return R.ok().data(permissionList);
    }

    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }

}
