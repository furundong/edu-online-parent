package com.example.serviceapi.edu;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.response.R;
import com.example.commonutils.entity.EduSubject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * 课程科目(EduSubject)表控制层
 *
 * @author makejava
 * @since 2020-09-03 15:06:59
 */
public interface EduSubjectControllerApi {

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param eduSubject 查询实体
     * @return 所有数据
     */
    @GetMapping
     R selectAll(Page<EduSubject> page, EduSubject eduSubject) ;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
     R selectOne(@PathVariable("id") Serializable id) ;

    @GetMapping("nestedList")
     R nestedList();

    /**
     * 文件新增数据
     *
     */
    @PostMapping("upload")
     R upload(MultipartFile file);

    /**
     * 将最新的课程数据上传到oss
     */
    @GetMapping("latestSubjectUpdate")
     R eduSubjectService();

    /**
     * 新增数据
     *
     * @param eduSubject 实体对象
     * @return 新增结果
     */
    @PostMapping
     R insert(@RequestBody EduSubject eduSubject) ;

    /**
     * 修改数据
     *
     * @param eduSubject 实体对象
     * @return 修改结果
     */
    @PutMapping
     R update(@RequestBody EduSubject eduSubject);

    /**
     * 删除数据单条数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("{id}")
     R deleteById(@PathVariable("id") String id) ;

    /**
     * 批量删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batchDelete")
     R delete(@RequestParam("idList") List<Long> idList);
}
