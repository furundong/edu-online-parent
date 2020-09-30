package com.example.serivceedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.EduCourseDescription;
import com.example.serivceedu.dao.EduCourseDescriptionDao;
import com.example.serivceedu.service.EduCourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * 课程简介(EduCourseDescription)表服务实现类
 *
 * @author makejava
 * @since 2020-09-04 16:02:40
 */
@Service("eduCourseDescriptionService")
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionDao, EduCourseDescription> implements EduCourseDescriptionService {

}
