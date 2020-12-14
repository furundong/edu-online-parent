package com.example.serivceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.commonutils.entity.EduSubject;
import com.example.commonutils.entity.vo.SubjectNestedVo;
import com.example.serivceedu.service.EduSubjectService;
import com.example.serviceapi.edu.EduSubjectControllerApi;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 课程科目(EduSubject)表控制层
 *
 * @author makejava
 * @since 2020-09-03 15:06:59
 */
@RestController
@RequestMapping("edu/subject")
//@CrossOrigin
public class EduSubjectController implements EduSubjectControllerApi {
    /**
     * 服务对象
     */
    @Resource
    private EduSubjectService eduSubjectService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param eduSubject 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<EduSubject> page, EduSubject eduSubject) {
        Page<EduSubject> entity = this.eduSubjectService.page(page, new QueryWrapper<>(eduSubject));
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
        EduSubject entityById = this.eduSubjectService.getById(id);
        return R.ok().data(entityById);
    }

    @GetMapping("nestedList")
    public R nestedList(){
//        List<SubjectNestedVo> subjectNestedVoList = eduSubjectService.nestedList();
        List<SubjectNestedVo> subjectNestedVoList = eduSubjectService.selectSqlNestedTwoLevel();
        return R.ok().data(subjectNestedVoList);
    }

    /**
     * 文件新增数据
     *
     */
    @PostMapping("upload")
    public R upload(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //这里使用easyExcel，那么监听器中的service不能通过spring注入.
        eduSubjectService.importSubjectData(file,eduSubjectService);
        //判断返回集合是否为空
        return R.ok();
    }

    /**
     * 将最新的课程数据上传到oss
     */
    @GetMapping("latestSubjectUpdate")
    public R eduSubjectService(){
        eduSubjectService.eduSubjectService();
        return R.ok().message("已更新至最新");
    }

    /**
     * 新增数据
     *
     * @param eduSubject 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody EduSubject eduSubject) {
        this.eduSubjectService.save(eduSubject);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param eduSubject 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody EduSubject eduSubject) {
        this.eduSubjectService.updateById(eduSubject);
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
        this.eduSubjectService.removeById(id);
        return R.ok();
    }

    /**
     * 批量删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batchDelete")
    public R delete(@RequestParam("idList") List<Long> idList) {
        this.eduSubjectService.removeByIds(idList);
        return R.ok();
    }
}
