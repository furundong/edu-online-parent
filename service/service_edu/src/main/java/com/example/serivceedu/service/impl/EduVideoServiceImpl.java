package com.example.serivceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.EduVideo;
import com.example.serivceedu.client.VodClient;
import com.example.serivceedu.dao.EduVideoDao;
import com.example.serivceedu.service.EduVideoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程视频(EduVideo)表服务实现类
 *
 * @author makejava
 * @since 2020-09-08 13:50:22
 */
@Service("eduVideoService")
public class EduVideoServiceImpl extends ServiceImpl<EduVideoDao, EduVideo> implements EduVideoService {

    @Resource
    VodClient vodClient;

    @Override
    public boolean removeById(String id) {

        //查询云端视频id
        EduVideo video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除视频资源
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }

        int result = baseMapper.deleteById(id);
        return result > 0;
    }


    @Override
    public boolean removeByCourseId(String courseId) {

        //根据课程id查询所有视频列表
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(queryWrapper);

        //得到所有视频列表的云端原始视频id
        List<String> videoSourceIdList = new ArrayList<>();
        for (EduVideo video : videoList) {
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoSourceIdList.add(videoSourceId);
            }
        }

        //调用vod服务删除远程视频
        if(videoSourceIdList.size() > 0){
            vodClient.removeVideoList(videoSourceIdList);
        }

        //删除video表示的记录
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        int count = baseMapper.delete(queryWrapper2);
        return count > 0;
    }

}
