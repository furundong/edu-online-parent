package com.example.serivceedu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commonutils.entity.EduSubject;
import com.example.commonutils.entity.vo.SubjectNestedVo;

import java.util.List;

/**
 * 课程科目(EduSubject)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-03 14:46:48
 */
public interface EduSubjectDao extends BaseMapper<EduSubject> {
    List<SubjectNestedVo> selectSqlNestedTwoLevel(); // 通过sql创建二级菜单
}
