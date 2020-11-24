package com.example.serivceedu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commonutils.entity.EduCourse;
import com.example.commonutils.entity.vo.CoursePublishVo;
import com.example.commonutils.entity.vo.CourseWebVo;

/**
 * 课程(EduCourse)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-04 16:02:39
 */
public interface EduCourseDao extends BaseMapper<EduCourse> {
    CoursePublishVo selectCoursePublishVoById(String id);

    CourseWebVo selectInfoWebById(String id);
}
