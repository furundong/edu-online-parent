package com.example.serivceedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.EduCourse;
import com.example.commonutils.entity.EduCourseDescription;
import com.example.commonutils.entity.dto.CourseInfoForm;
import com.example.commonutils.entity.vo.CoursePublishVo;
import com.example.commonutils.entity.vo.CourseWebVo;
import com.example.commonutils.response.ResultCodeEnum;
import com.example.serivceedu.client.OssClient;
import com.example.serivceedu.dao.EduCourseDao;
import com.example.serivceedu.service.EduChapterService;
import com.example.serivceedu.service.EduCourseDescriptionService;
import com.example.serivceedu.service.EduCourseService;
import com.example.serivceedu.service.EduVideoService;
import com.example.servicebase.exceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 课程(EduCourse)表服务实现类
 *
 * @author makejava
 * @since 2020-09-04 16:02:40
 */
@Service("eduCourseService")
public class EduCourseServiceImpl extends ServiceImpl<EduCourseDao, EduCourse> implements EduCourseService {

    @Resource
    private EduCourseDescriptionService courseDescriptionService;

    @Resource
    private EduCourseDao eduCourseDao;

    @Resource
    private EduChapterService eduChapterService;

    @Resource
    private EduVideoService eduVideoService;

    @Resource
    private OssClient ossClient;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        EduCourse course = new EduCourse();
        course.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.save(course);
        if (!resultCourseInfo) {
//            throw new GuliException(20001, "课程信息保存失败");
            throw new GuliException(ResultCodeEnum.UNKNOWN_REASON);
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = courseDescriptionService.save(courseDescription);
        if (!resultDescription) {
//            throw new GuliException(20001, "课程详情信息保存失败");
            throw new GuliException(ResultCodeEnum.UNKNOWN_REASON);
        }

        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {
        EduCourse course = this.getById(id);
        if (course == null) {
            throw new GuliException(ResultCodeEnum.UNKNOWN_DATA);
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);

        EduCourseDescription courseDescription = courseDescriptionService.getById(id);
        if (courseDescription != null)
            courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.updateById(course);
        if (!resultCourseInfo) {
            throw new GuliException(ResultCodeEnum.UNKNOWN_REASON);
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = courseDescriptionService.updateById(courseDescription);
        if (!resultDescription) {
            throw new GuliException(ResultCodeEnum.UNKNOWN_REASON);
        }
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return eduCourseDao.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus(EduCourse.COURSE_NORMAL);
        int count = baseMapper.updateById(course);
        return count > 0;
    }

    @Override
    public CourseWebVo selectInfoWebById(String id) {
        this.updatePageViewCount(id);
        return baseMapper.selectInfoWebById(id);
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }

    @Override
    public boolean removeById(Serializable id) {

        String strId = (String) id;
        CourseInfoForm formById = getCourseInfoFormById(strId);

        //根据id删除所有视频
        eduVideoService.removeByCourseId(strId);

        //根据id删除所有章节
        eduChapterService.removeByCourseId(strId);

        //根据id删除所有课程详情
        courseDescriptionService.removeById(id);

        //删除封面
        ossClient.removeVideo(formById.getCover());

        int result = baseMapper.deleteById(id);
        return result > 0;
    }
}
