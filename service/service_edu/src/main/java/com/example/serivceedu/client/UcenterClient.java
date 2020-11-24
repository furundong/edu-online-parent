package com.example.serivceedu.client;

import com.example.commonutils.entity.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-ucenter")
@Component
public interface UcenterClient {

    //根据用户id获取用户信息
    @GetMapping("/ucenter/getInfoUc")
    UcenterMember getInfoUc(@RequestParam("id") String id);

}
