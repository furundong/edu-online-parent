package com.example.serivceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.response.ResultCodeEnum;
import com.example.commonutils.entity.EduSubject;
import com.example.commonutils.entity.vo.ExcelSubjectData;
import com.example.serivceedu.service.EduSubjectService;
import com.example.servicebase.exceptionHandler.GuliException;

import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * create by Freedom on 2020/9/3
 */
// 有个很重要的点 SubjectExcelListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
    private static final int BATCH_COUNT = 5;
    //每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
    List<ExcelSubjectData> list = new ArrayList<>();

    public final EduSubjectService eduSubjectService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    /**
     * 这个每一条数据解析都会来调用，如果说使用表头数据，请用invokeHeadMap方法
     *
     * @param subjectData
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelSubjectData subjectData, AnalysisContext context) {
        if(subjectData == null){
            throw new GuliException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }

        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if(existOneSubject == null) { //没有相同一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());//一级分类名称
            eduSubjectService.save(existOneSubject); //po对象是引用传递
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            eduSubjectService.save(existTwoSubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String oneSubjectName) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",oneSubjectName);
        wrapper.eq("parent_id","0");
        return eduSubjectService.getOne(wrapper);
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return subjectService.getOne(wrapper);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
    }
}
