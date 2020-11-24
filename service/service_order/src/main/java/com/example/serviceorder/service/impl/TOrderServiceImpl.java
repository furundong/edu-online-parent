package com.example.serviceorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.TOrder;
import com.example.commonutils.entity.UcenterMember;
import com.example.commonutils.entity.dto.CourseInfoForm;
import com.example.serviceorder.client.EduClient;
import com.example.serviceorder.client.UcenterClient;
import com.example.serviceorder.dao.TOrderDao;
import com.example.serviceorder.service.TOrderService;
import com.example.serviceorder.utils.OrderNoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create by Freedom on 2020/11/20
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderDao, TOrder> implements TOrderService {

    @Resource
    private EduClient eduClient;

    @Resource
    private UcenterClient ucenterClient;

    //创建订单
    @Override
    public String saveOrder(String courseId, String memberId) {

        //远程调用课程服务，根据课程id获取课程信息
        CourseInfoForm courseDto = eduClient.getCourseInfoDto(courseId);

        //远程调用用户服务，根据用户id获取用户信息
        UcenterMember ucenterMember = ucenterClient.getInfoUc(memberId);

        //创建订单
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseDto.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMember.getMobile());
        order.setNickname(ucenterMember.getNickname());
        order.setStatus(false);
        order.setPayType(true);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
