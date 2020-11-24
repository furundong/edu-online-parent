package com.example.serivceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.EduCourse;
import com.example.commonutils.entity.dto.CourseInfoForm;
import com.example.commonutils.entity.vo.CoursePublishVo;
import com.example.commonutils.entity.vo.CourseWebVo;

/**
 * 课程(EduCourse)表服务接口
 *
 * @author makejava
 * @since 2020-09-04 16:02:39
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);//根据CourseInfoForm保存eduCourse

    CourseInfoForm getCourseInfoFormById(String id); //根据id查找CourseInfoForm回显数据

    void updateCourseInfoById(CourseInfoForm courseInfoForm);//根据CourseInfoForm修改eduCourse

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id); //发布课程。修改状态COURSE_NORMAL

    CourseWebVo selectInfoWebById(String courseId);

    /**
     * 更新课程浏览数
     * @param id
     */
    void updatePageViewCount(String id);
}
