package com.example.serivceedu.client;

import com.example.commonutils.response.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create by Freedom on 2020/9/18
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }

    @Override
    public R removeVideoList(List<String> videoIdList) {
        return R.error().message("time out");
    }
}
