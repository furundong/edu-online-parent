package com.example.serivceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.EduSubject;
import com.example.commonutils.entity.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 课程科目(EduSubject)表服务接口
 *
 * @author makejava
 * @since 2020-09-03 14:46:49
 */
public interface EduSubjectService extends IService<EduSubject> {

    void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService);

    List<SubjectNestedVo> nestedList();

    List<SubjectNestedVo> selectSqlNestedTwoLevel(); // 通过sql创建二级菜单

    void eduSubjectService();

}
