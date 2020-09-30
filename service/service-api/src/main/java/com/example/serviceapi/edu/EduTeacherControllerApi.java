package com.example.serviceapi.edu;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.response.R;
import com.example.commonutils.entity.EduTeacher;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public interface EduTeacherControllerApi {
    R selectAll(Page<EduTeacher> page, EduTeacher eduTeacher) ;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    R selectOne(@PathVariable("id") Serializable id) ;

    /**
     * 新增数据
     *
     * @param eduTeacher 实体对象
     * @return 新增结果
     */
    @PostMapping
     R insert(@RequestBody EduTeacher eduTeacher);

    /**
     * 修改数据
     *
     * @param eduTeacher 实体对象
     * @return 修改结果
     */
    @PutMapping
     R update(@RequestBody EduTeacher eduTeacher) ;

    @DeleteMapping("{id}")
     R deleteById(@PathVariable("id") String id) ;

    /**
     * 批量删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batchDelete")
     R delete(@RequestParam("idList") List<String> idList);
}
