package com.example.serviceorder.controller;


import com.example.commonutils.response.R;
import com.example.serviceorder.service.TPayLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 支付日志表(TPayLog)表控制层
 *
 * @author makejava
 * @since 2020-11-20 09:37:12
 */
@RestController
@RequestMapping("/order/log")
public class TPayLogController  {

    @Resource
    private TPayLogService payService;

    /**
     * 生成二维码
     *
     * @return
     */
    @GetMapping("/createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map<String,Object> map = payService.createNative(orderNo);
        return R.ok().data(map);
    }

    //获取支付状态接口
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payService.queryPayStatus(orderNo);
        if (map == null) {//出错
            return R.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }

        return R.ok().code(25000).message("支付中");
    }

}
