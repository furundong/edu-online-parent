package com.example.serviceapi.edu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.EduCourseDescription;
import com.example.commonutils.response.R;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 课程简介(EduCourseDescription)表服务接口
 *
 * @author makejava
 * @since 2020-09-07 09:04:13
 */
public interface EduCourseDescriptionControllerApi {

    /**
     * 分页查询所有数据
     *
     * @param page   分页对象
     * @param entity 查询实体
     * @return 所有数据
     */
    @GetMapping
    R selectAll(Page<EduCourseDescription> page, EduCourseDescription entity);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    R selectOne(@PathVariable("id") Serializable id);

    /**
     * 新增数据
     *
     * @param entity 实体对象
     * @return 新增结果
     */
    @PostMapping
    R insert(@RequestBody EduCourseDescription entity);

    /**
     * 修改数据
     *
     * @param entity 实体对象
     * @return 修改结果
     */
    @PutMapping
    R update(@RequestBody EduCourseDescription entity);

    /**
     * 删除数据单条数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    R deleteById(@PathVariable("id") String id);

    /**
     * 批量删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batchDelete")
    R delete(@RequestParam("idList") List<String> idList);

}
