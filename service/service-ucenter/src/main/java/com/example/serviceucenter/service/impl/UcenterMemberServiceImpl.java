package com.example.serviceucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.UcenterMember;
import com.example.commonutils.entity.vo.LoginVo;
import com.example.commonutils.entity.vo.RegisterVo;
import com.example.commonutils.utils.JwtUtils;
import com.example.commonutils.utils.MD5;
import com.example.servicebase.exceptionHandler.GuliException;
import com.example.serviceucenter.dao.UcenterMemberDao;
import com.example.serviceucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 会员表(UcenterMember)表服务实现类
 *
 * @author makejava
 * @since 2020-09-25 16:14:41
 */
@Service("ucenterMemberService")
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberDao, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 会员登录
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //校验参数
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password)) {
            throw new GuliException(false,20001,"error");
        }

        //获取会员
        UcenterMember member = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(null == member) {
            throw new GuliException(false,20001,"该电话不存在");
        }

        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(false,20001,"账号或密码错误");
        }

        //校验是否被禁用
        if(member.getIsDisabled()) {
            throw new GuliException(false,20001,"校验是否被禁用");
        }

        //使用JWT生成token字符串
        return JwtUtils.getJwtToken(member.getId(), member.getNickname());
    }

    /**
     * 会员注册
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new GuliException(false,20001,"error");
        }

        //先发送验证码，这里就检验

        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(mobleCode)) {
            throw new GuliException(false,20001,"验证码已经失效，请重新发送");
        }

        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(count > 0) {
            throw new GuliException(false,20001,"手机号码已被注册");
        }

        //添加注册信息到数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }

    @Override
    public UcenterMember getLoginVo(String memberId) {
        return baseMapper.selectById(memberId);
    }
}
