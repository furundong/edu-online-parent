package com.example.serviceucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.UcenterMember;
import com.example.commonutils.entity.vo.LoginVo;
import com.example.commonutils.entity.vo.RegisterVo;

import java.sql.Timestamp;

/**
 * 会员表(UcenterMember)表服务接口
 *
 * @author makejava
 * @since 2020-09-25 16:14:40
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    UcenterMember getLoginVo(String memberId);

    Integer countRegisterByDay(Timestamp day);
}
