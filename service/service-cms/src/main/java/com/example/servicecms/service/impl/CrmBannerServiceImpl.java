package com.example.servicecms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.CrmBanner;
import com.example.servicecms.dao.CrmBannerDao;
import com.example.servicecms.service.CrmBannerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页banner表(CrmBanner)表服务实现类
 *
 * @author makejava
 * @since 2020-09-24 09:22:40
 */
@Service("crmBannerService")
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerDao, CrmBanner> implements CrmBannerService {

    @Cacheable( key = "'selectIndexList'" ,value = "banner")
    @Override
    public List<CrmBanner> list(Wrapper<CrmBanner> queryWrapper) {
        return super.list(queryWrapper);
    }


}
