package com.example.serivceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.response.ResultCodeEnum;
import com.example.serivceedu.dao.EduSubjectDao;
import com.example.commonutils.entity.EduSubject;
import com.example.commonutils.entity.vo.ExcelSubjectData;
import com.example.commonutils.entity.vo.SubjectNestedVo;
import com.example.commonutils.entity.vo.SubjectVo;
import com.example.serivceedu.listener.SubjectExcelListener;
import com.example.serivceedu.service.EduSubjectService;
import com.example.servicebase.exceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 课程科目(EduSubject)表服务实现类
 *
 * @author makejava
 * @since 2020-09-03 14:46:49
 */
@Service("eduSubjectService")
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectDao, EduSubject> implements EduSubjectService {

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            throw new GuliException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终要的到的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();

        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);

        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);

        //填充一级分类vo数据
        int count = subjects.size();
        for (EduSubject EduSubject : subjects) {
            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(EduSubject, subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);

            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (EduSubject subSubject : subSubjects) {

                if (EduSubject.getId().equals(subSubject.getParentId())) {

                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    subjectVoArrayList.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }


        return subjectNestedVoArrayList;
    }

    @Override
    public List<SubjectNestedVo> selectSqlNestedTwoLevel() {
        return baseMapper.selectSqlNestedTwoLevel();
    }

    @Override
    public void eduSubjectService() {
//        List<SubjectNestedVo> list = baseMapper.selectSqlNestedTwoLevel();

    }
}
