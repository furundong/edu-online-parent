package com.example.serviceorder.client;

import com.example.commonutils.entity.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //根据课程id查询课程信息
    @GetMapping("/ucenter/getInfoUc")
    UcenterMember getInfoUc(@RequestParam("id") String id);

}
