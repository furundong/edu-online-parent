package com.example.serviceacl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.AclRole;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.serviceacl.service.AclRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (AclRole)表控制层
 *
 * @author makejava
 * @since 2020-11-26 13:41:16
 */
@RestController
@RequestMapping("/admin/acl/role")
public class AclRoleController {
    /**
     * 服务对象
     */
    @Resource
    private AclRoleService aclRoleService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param aclRole 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<AclRole> page, AclRole aclRole) {
        Page<AclRole> entity = this.aclRoleService.page(page, new QueryWrapper<>(aclRole));
        return R.ok().data(new PageResult<>(entity.getTotal(), entity.getRecords()));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        AclRole entityById = this.aclRoleService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param aclRole 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody AclRole aclRole) {
        this.aclRoleService.save(aclRole);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param aclRole 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody AclRole aclRole) {
        this.aclRoleService.updateById(aclRole);
        return R.ok();
    }

    /**
     * 删除数据单条数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable("id") String id) {
        this.aclRoleService.removeById(id);
        return R.ok();
    }

    /**
     * 批量删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batchDelete")
    public R delete(@RequestParam("idList") List<String> idList) {
        this.aclRoleService.removeByIds(idList);
        return R.ok();
    }
}
