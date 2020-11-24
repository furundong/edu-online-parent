package com.example.servicevod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.example.commonutils.response.R;
import com.example.servicevod.service.VideoService;
import com.example.servicevod.util.AliyunVodSDKUtils;
import com.example.servicevod.util.ConstantPropertiesUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * create by Freedom on 2020/9/10
 */
@RestController
@RequestMapping("/vod/video")
public class VideoAdminController {

    @Resource
    private VideoService videoService;

    /**
     * 后端获取播放凭证
     * @param videoId videoId
     * @return R
     * @throws Exception Exception
     */
    @GetMapping("get-play-auth/{videoId}")
    public R getVideoPlayAuth(@PathVariable("videoId") String videoId) throws Exception {

        //获取阿里云存储相关常量
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        //初始化
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);

        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);

        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        //得到播放凭证
        String playAuth = response.getPlayAuth();

        //封面地址
        final String coverURL = response.getVideoMeta().getCoverURL();

        //返回结果
        final HashMap<String, Object> result = new HashMap<>();
        result.put("playAuth",playAuth);
        result.put("cover",coverURL);
        return R.ok().message("获取凭证成功").data(result);
    }

    /**
     * 上传视频
     * @param file file
     * @return R
     */
    @PostMapping("upload")
    public R uploadVideo(@RequestParam("file") MultipartFile file) {

        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data(videoId);
    }

    /**
     * 删除视频
     * @param videoId videoId
     * @return R
     */
    @DeleteMapping("{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId){
        videoService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    /**
     * 批量删除视频
     * @param videoIdList videoIdList
     * @return R
     */
    @DeleteMapping("delete-batch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList){

        videoService.removeVideoList(videoIdList);
        return R.ok().message("视频删除成功");
    }
}
