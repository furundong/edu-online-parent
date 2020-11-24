package com.example.serviceorder.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.entity.TOrder;
import com.example.commonutils.response.R;
import com.example.commonutils.utils.JwtUtils;
import com.example.serviceorder.service.TOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * create by Freedom on 2020/11/20
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class TOrderController {

    @Resource
    private TOrderService orderService;

    //根据课程id和用户id创建订单，返回订单id
    @PostMapping("createOrder/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request) {
        String orderId = orderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data(orderId);
    }

    //根据id获取订单信息接口
    @GetMapping("getOrder/{orderId}")
    public R get(@PathVariable String orderId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(wrapper);
        return R.ok().data(order);
    }

    @GetMapping("isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable String memberid,
                               @PathVariable String id) {
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<TOrder>().eq("member_id", memberid).eq("course_id", id).eq("status", 1));
        return count > 0;
    }
}
