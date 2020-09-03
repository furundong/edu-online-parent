package com.example.serivceedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.serivceedu.dao.EduSubjectDao;
import com.example.serivceedu.entity.EduSubject;
import com.example.serivceedu.service.EduSubjectService;
import org.springframework.stereotype.Service;

/**
 * 课程科目(EduSubject)表服务实现类
 *
 * @author makejava
 * @since 2020-09-03 14:46:49
 */
@Service("eduSubjectService")
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectDao, EduSubject> implements EduSubjectService {

}
