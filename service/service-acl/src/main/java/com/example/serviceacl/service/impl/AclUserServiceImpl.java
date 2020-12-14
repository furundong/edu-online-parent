package com.example.serviceacl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.AclRole;
import com.example.commonutils.entity.AclUser;
import com.example.serviceacl.dao.AclUserDao;
import com.example.serviceacl.service.AclPermissionService;
import com.example.serviceacl.service.AclRoleService;
import com.example.serviceacl.service.AclUserService;
import com.example.servicebase.exceptionHandler.GuliException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户表(AclUser)表服务实现类
 *
 * @author makejava
 * @since 2020-11-26 13:41:18
 */
@Service("aclUserService")
public class AclUserServiceImpl extends ServiceImpl<AclUserDao, AclUser> implements AclUserService {

    @Resource
    AclRoleService aclRoleService;

    @Resource
    AclPermissionService permissionService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public AclUser selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<AclUser>().eq("username",username));
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        AclUser user = baseMapper.selectOne(new QueryWrapper<AclUser>().eq("username",username));
        if (null == user) {
            throw new GuliException(false,99999,"用户不存在");
        }

        //根据用户id获取角色
        List<AclRole> roleList = aclRoleService.selectRoleByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(AclRole::getRoleName).collect(Collectors.toList());
        if(roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        redisTemplate.opsForValue().set(username, permissionValueList);

        result.put("name", user.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }
}
