package com.example.serivceedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.EduCourseDescription;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.serivceedu.service.EduCourseDescriptionService;
import com.example.serviceapi.edu.EduCourseDescriptionControllerApi;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 课程简介(EduCourseDescription)表控制层
 *
 * @author makejava
 * @since 2020-09-07 09:06:05
 */
@RestController
@RequestMapping("edu/courseDescription")
public class EduCourseDescriptionController implements EduCourseDescriptionControllerApi {
    /**
     * 服务对象
     */
    @Resource
    private EduCourseDescriptionService eduCourseDescriptionService;

    /**
     * 分页查询所有数据
     *
     * @param page                 分页对象
     * @param eduCourseDescription 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<EduCourseDescription> page, EduCourseDescription eduCourseDescription) {
        Page<EduCourseDescription> entity = this.eduCourseDescriptionService.page(page, new QueryWrapper<>(eduCourseDescription));
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
        EduCourseDescription entityById = this.eduCourseDescriptionService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param eduCourseDescription 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody EduCourseDescription eduCourseDescription) {
        this.eduCourseDescriptionService.save(eduCourseDescription);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param eduCourseDescription 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody EduCourseDescription eduCourseDescription) {
        this.eduCourseDescriptionService.updateById(eduCourseDescription);
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
        this.eduCourseDescriptionService.removeById(id);
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
        this.eduCourseDescriptionService.removeByIds(idList);
        return R.ok();
    }
}
