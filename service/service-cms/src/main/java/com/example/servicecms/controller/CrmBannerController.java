package com.example.servicecms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.CrmBanner;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.servicecms.service.CrmBannerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 首页banner表(CrmBanner)表控制层
 *
 * @author makejava
 * @since 2020-09-24 09:22:40
 */
@RestController
@RequestMapping("cms/service/banner")
public class CrmBannerController  {
    /**
     * 服务对象
     */
    @Resource
    private CrmBannerService crmBannerService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param crmBanner 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<CrmBanner> page, CrmBanner crmBanner) {
        Page<CrmBanner> entity = this.crmBannerService.page(page, new QueryWrapper<>(crmBanner));
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
        CrmBanner entityById = this.crmBannerService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param crmBanner 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody CrmBanner crmBanner) {
        this.crmBannerService.save(crmBanner);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param crmBanner 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody CrmBanner crmBanner) {
        this.crmBannerService.updateById(crmBanner);
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
        this.crmBannerService.removeById(id);
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
        this.crmBannerService.removeByIds(idList);
        return R.ok();
    }
}
