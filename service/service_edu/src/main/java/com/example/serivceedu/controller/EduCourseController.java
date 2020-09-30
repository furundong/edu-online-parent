package com.example.serivceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.EduCourse;
import com.example.commonutils.entity.dto.CourseInfoForm;
import com.example.commonutils.entity.vo.CoursePublishVo;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.serivceedu.service.EduCourseService;
import com.example.serviceapi.edu.EduCourseControllerApi;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 课程(EduCourse)表控制层
 *
 * @author makejava
 * @since 2020-09-07 09:09:35
 */
@RestController
@RequestMapping("edu/course")
public class EduCourseController implements EduCourseControllerApi {
    /**
     * 服务对象
     */
    @Resource
    private EduCourseService eduCourseService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param eduCourse 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<EduCourse> page,EduCourse eduCourse) {
        Page<EduCourse> entity = this.eduCourseService.page(page, new QueryWrapper<>(eduCourse));
        return R.ok().data(new PageResult<>(entity.getTotal(), entity.getRecords()));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable("id") Serializable id) {
        EduCourse entityById = this.eduCourseService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param eduCourse 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody EduCourse eduCourse) {
        this.eduCourseService.save(eduCourse);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param eduCourse 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody EduCourse eduCourse) {
        this.eduCourseService.updateById(eduCourse);
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
        this.eduCourseService.removeById(id);
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
        this.eduCourseService.removeByIds(idList);
        return R.ok();
    }

    /**
     * 根据CourseInfoForm保存eduCourse
     * @param courseInfoForm courseInfoForm
     * @return R
     */
    @PostMapping("save-course-info")
    public R saveCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
        if(!StringUtils.isEmpty(courseId)){
            return R.ok().data(courseId);
        }else{
            return R.error().message("保存失败");
        }
    }

    /**
     *根据id查找CourseInfoForm回显数据
     * @param id id
     * @return R
     */
    @GetMapping("course-info/{id}")
    public R getById(@PathVariable("id") String id){
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfoFormById(id);
        return R.ok().data(courseInfoForm);
    }

    /**
     * 根据ID获取课程发布信息
     *
     * @param courseInfoForm courseInfoForm
     * @param id id
     * @return R
     */
    @PutMapping("update-course-info/{id}")
    public R updateCourseInfoById(@RequestBody CourseInfoForm courseInfoForm, @PathVariable String id){
        eduCourseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }

    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(@PathVariable String id){
        CoursePublishVo courseInfoForm = eduCourseService.getCoursePublishVoById(id);
        return R.ok().data(courseInfoForm);
    }

    /**
     * 根据id发布课程
     * @param id id
     * @return R
     */
    @PutMapping("publish-course/{id}")
    public R publishCourseById( @PathVariable String id){
        eduCourseService.publishCourseById(id);
        return R.ok();
    }
}
