package com.example.servicecms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.entity.CrmBanner;
import com.example.commonutils.response.R;
import com.example.servicecms.service.CrmBannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页banner表(CrmBanner)表控制层
 *
 * @author makejava
 * @since 2020-09-24 09:22:40
 */
@RestController
@RequestMapping("/cms/banner")
public class BannerApiController {
    /**
     * 服务对象
     */
    @Resource
    private CrmBannerService crmBannerService;

    /**
     * 获取首页banner
     * @return List<CrmBanner>
     */
    @GetMapping("getAllBanner")
    public R index() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        wrapper.last("limit 4");
        List<CrmBanner> list = crmBannerService.list(wrapper);
        return R.ok().data(list);
    }
}
