package com.example.serivceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.EduTeacher;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.serivceedu.service.EduTeacherService;
import com.example.serviceapi.edu.EduTeacherControllerApi;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 讲师(EduTeacher)表控制层
 *
 * @author makejava
 * @since 2020-09-04 16:02:53
 */
@RestController
@RequestMapping("edu/teacher")
public class EduTeacherController implements EduTeacherControllerApi {
    /**
     * 服务对象
     */
    @Resource
    private EduTeacherService eduTeacherService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param eduTeacher 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<EduTeacher> page, EduTeacher eduTeacher) {
        Page<EduTeacher> entity = this.eduTeacherService.page(page, new QueryWrapper<>(eduTeacher));
        return R.ok().data(new PageResult<>(entity.getTotal(), entity.getRecords()));
    }

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("findAll")
    public R selectAllNoPage() {
        return R.ok().data(this.eduTeacherService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        EduTeacher entityById = this.eduTeacherService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param eduTeacher 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody EduTeacher eduTeacher) {
        this.eduTeacherService.save(eduTeacher);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param eduTeacher 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody EduTeacher eduTeacher) {
        this.eduTeacherService.updateById(eduTeacher);
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
        this.eduTeacherService.removeById(id);
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
        this.eduTeacherService.removeByIds(idList);
        return R.ok();
    }
}
