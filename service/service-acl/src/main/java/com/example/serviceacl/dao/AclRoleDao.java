package com.example.serviceacl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commonutils.entity.AclRole;

import java.util.List;

/**
 * (AclRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-26 13:41:12
 */
public interface AclRoleDao extends BaseMapper<AclRole> {

    List<AclRole> selectRoleByUserId(String id);
}
