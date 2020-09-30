package com.example.serivceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.EduChapter;
import com.example.commonutils.entity.vo.ChapterVo;

import java.util.List;

/**
 * 课程(EduChapter)表服务接口
 *
 * @author makejava
 * @since 2020-09-04 16:02:39
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> nestedList(String courseId);

    void removeByCourseId(String id);
}
