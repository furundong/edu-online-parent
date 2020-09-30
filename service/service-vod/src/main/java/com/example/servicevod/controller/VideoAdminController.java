package com.example.servicevod.controller;

import com.example.commonutils.response.R;
import com.example.servicevod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * create by Freedom on 2020/9/10
 */
@RestController
@RequestMapping("/vod/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(@RequestParam("file") MultipartFile file) {

        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data(videoId);
    }

    @DeleteMapping("{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId){
        videoService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    /**
     * 批量删除视频
     * @param videoIdList videoIdList
     * @return
     */
    @DeleteMapping("delete-batch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList){

        videoService.removeVideoList(videoIdList);
        return R.ok().message("视频删除成功");
    }
}
