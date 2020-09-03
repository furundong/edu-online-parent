package com.example.serivceedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.serivceedu.dao.EduTeacherDao;
import com.example.serivceedu.entity.EduTeacher;
import com.example.serivceedu.service.EduTeacherService;
import org.springframework.stereotype.Service;

/**
 * 讲师(EduTeacher)表服务实现类
 *
 * @author makejava
 * @since 2020-08-17 09:00:46
 */
@Service("eduTeacherService")
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherDao, EduTeacher> implements EduTeacherService {

}
