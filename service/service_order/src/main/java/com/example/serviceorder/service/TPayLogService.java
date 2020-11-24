package com.example.serviceorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.TPayLog;

import java.util.Map;

/**
 * 支付日志表(TPayLog)表服务接口
 *
 * @author makejava
 * @since 2020-11-20 09:37:12
 */
public interface TPayLogService extends IService<TPayLog> {

    Map<String, Object> createNative(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderNo);
}
