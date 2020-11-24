package com.example.serviceorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.TOrder;

/**
 * create by Freedom on 2020/11/20
 */
public interface TOrderService extends IService<TOrder> {
    String saveOrder(String courseId, String memberIdByJwtToken);
}
