package com.example.serivceedu;

import com.alibaba.excel.EasyExcel;
import com.example.commonutils.entity.vo.ExcelSubjectData;
import com.example.commonutils.entity.vo.SubjectNestedVo;
import com.example.commonutils.entity.vo.SubjectVo;
import com.example.serivceedu.dao.EduSubjectDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * create by Freedom on 2020/9/3
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceEduSpringbootTest {

    @Resource
    EduSubjectDao eduSubjectDao;
    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void redisTEst(){
        redisTemplate.opsForValue().set("phone", "code",3, TimeUnit.MINUTES);
        System.out.println(redisTemplate.opsForValue().get("phone"));
    }

    @Test
    public void setEduSubjectDaoTest(){
        List<SubjectNestedVo> subjectVos = eduSubjectDao.selectSqlNestedTwoLevel();
        subjectVos.forEach(e->{
            System.out.println("e = " + e);
        });
    }

    @Test
    public void allEduSubjectDaoTest(){
        List<SubjectNestedVo> subjectNestedVos = eduSubjectDao.selectSqlNestedTwoLevel();
        List<ExcelSubjectData> target = new ArrayList<>();
        for (SubjectNestedVo nestedVo : subjectNestedVos) {
            String title = nestedVo.getTitle();
            List<SubjectVo> children = nestedVo.getChildren();
            for (SubjectVo child : children) {
                String title1 = child.getTitle();
                ExcelSubjectData excelSubjectData = new ExcelSubjectData(title,title1);
                target.add(excelSubjectData);
            }
        }
        EasyExcel.write("https://freedom98.oss-cn-beijing.aliyuncs.com/excel/课程分类列表模板.xls", ExcelSubjectData.class).sheet("模板").doWrite(target);
    }
}

