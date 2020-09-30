package com.example.servicevod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * create by Freedom on 2020/9/10
 */
public interface VideoService {
    String uploadVideo(MultipartFile file);
    void removeVideo(String videoId);//删除云端视频
    void removeVideoList(List<String> videoIdList);
}
