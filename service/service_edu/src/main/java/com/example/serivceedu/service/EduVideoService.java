package com.example.serivceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.EduVideo;

/**
 * 课程视频(EduVideo)表服务接口
 *
 * @author makejava
 * @since 2020-09-08 13:50:22
 */
public interface EduVideoService extends IService<EduVideo> {
    boolean removeById(String id);
    boolean removeByCourseId(String courseId);
}
