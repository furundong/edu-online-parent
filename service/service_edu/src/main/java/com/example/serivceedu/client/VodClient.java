package com.example.serivceedu.client;

import com.example.commonutils.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * create by Freedom on 2020/9/17
 */
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping(value = "/vod/video/{videoId}")
    R removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping(value = "/vod/video/delete-batch")
    R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
