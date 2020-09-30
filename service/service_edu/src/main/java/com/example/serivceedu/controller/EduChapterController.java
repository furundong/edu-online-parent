package com.example.serivceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.EduChapter;
import com.example.commonutils.entity.vo.ChapterVo;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.serivceedu.service.EduChapterService;
import com.example.serviceapi.edu.EduChapterControllerApi;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 课程(EduChapter)表控制层
 *
 * @author makejava
 * @since 2020-09-07 09:08:50
 */
@RestController
@RequestMapping("edu/chapter")
public class EduChapterController implements EduChapterControllerApi {
    /**
     * 服务对象
     */
    @Resource
    private EduChapterService eduChapterService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param eduChapter 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<EduChapter> page, EduChapter eduChapter) {
        Page<EduChapter> entity = this.eduChapterService.page(page, new QueryWrapper<>(eduChapter));
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
        EduChapter entityById = this.eduChapterService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param eduChapter 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody EduChapter eduChapter) {
        this.eduChapterService.save(eduChapter);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param eduChapter 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody EduChapter eduChapter) {
        this.eduChapterService.updateById(eduChapter);
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
        this.eduChapterService.removeById(id);
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
        this.eduChapterService.removeByIds(idList);
        return R.ok();
    }


    @GetMapping("nested-list/{courseId}")
    public R nestedListByCourseId(@PathVariable("courseId") String courseId){

        List<ChapterVo> chapterVoList = eduChapterService.nestedList(courseId);
        return R.ok().data(chapterVoList);
    }
}
