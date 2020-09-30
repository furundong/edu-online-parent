package com.example.serivceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.EduVideo;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.serivceedu.service.EduVideoService;
import com.example.serviceapi.edu.EduVideoControllerApi;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 课程视频(EduVideo)表控制层
 *
 * @author makejava
 * @since 2020-09-08 13:50:23
 */
@RestController
@RequestMapping("edu/video")
public class EduVideoController implements EduVideoControllerApi {
    /**
     * 服务对象
     */
    @Resource
    private EduVideoService eduVideoService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param eduVideo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<EduVideo> page, EduVideo eduVideo) {
        Page<EduVideo> entity = this.eduVideoService.page(page, new QueryWrapper<>(eduVideo));
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
        EduVideo entityById = this.eduVideoService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param eduVideo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody EduVideo eduVideo) {
        this.eduVideoService.save(eduVideo);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param eduVideo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody EduVideo eduVideo) {
        this.eduVideoService.updateById(eduVideo);
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
        this.eduVideoService.removeById(id);
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
        this.eduVideoService.removeByIds(idList);
        return R.ok();
    }
}
