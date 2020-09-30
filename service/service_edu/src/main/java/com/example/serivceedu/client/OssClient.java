package com.example.serivceedu.client;

import com.example.commonutils.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * create by Freedom on 2020/9/17
 */
@FeignClient("service-oss")
@Component
public interface OssClient {
    @DeleteMapping(value = "/admin/oss/file")
    R removeVideo(@RequestParam("path") String path);

}
