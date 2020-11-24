package com.example.serviceucenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.entity.UcenterMember;
import com.example.commonutils.utils.JwtUtils;
import com.example.servicebase.exceptionHandler.GuliException;
import com.example.serviceucenter.service.UcenterMemberService;
import com.example.serviceucenter.util.ConstantPropertiesUtil;
import com.example.serviceucenter.util.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * create by Freedom on 2020/9/27
 */
@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Resource
    UcenterMemberService memberService;

    @GetMapping("callback")
    public String callback(String code, String state){

        //得到授权临时票据code
//        System.out.println("code = " + code);
//        System.out.println("state = " + state);

        //从redis中将state获取出来，和当前传入的state作比较
        //如果一致则放行，如果不一致则抛出异常：非法访问， 这个是防rscf的，但是这里不做。浪费时间

        //向认证服务器发送请求换取access_token,
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                code);

        String result;
        try {
            result = HttpClientUtils.get(accessTokenUrl);
            /*
             * {
             * "access_token":"ACCESS_TOKEN",  接口调用凭证
             * "expires_in":7200,   access_token接口调用凭证超时时间，单位（秒）
             * "refresh_token":"REFRESH_TOKEN",  用户刷新access_token
             * "openid":"OPENID",  授权用户唯一标识
             * "scope":"SCOPE",  用户授权的作用域，使用逗号（,）分隔
             * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL" 当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
             * }
             */
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(false,99999,"微信登录异常");
        }

        //解析json字符串
        Gson gson = new Gson();
        HashMap<String,Object> map = gson.fromJson(result, HashMap.class);
        String accessToken = (String)map.get("access_token");
        String openid = (String)map.get("openid"); // 这个openid是微信 授权用户唯一标识

        //查询数据库当前用用户是否曾经使用过微信登录
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = memberService.getOne(wrapper);

        if(member == null) {

            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";

            String userInfoUrl = String.format(baseUserInfoUrl,
                    accessToken,
                    openid);
            String userInfo;
            /*
            openid	普通用户的标识，对当前开发者帐号唯一
            nickname	普通用户昵称
            sex	普通用户性别，1为男性，2为女性
            province	普通用户个人资料填写的省份
            city	普通用户个人资料填写的城市
            country	国家，如中国为CN
            headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
            privilege	用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
            unionid	用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
             */
            try {
                userInfo = HttpClientUtils.get(userInfoUrl);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GuliException(false, 99999, "微信登陆失败");

            }
            HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
            String nickname = (String)userInfoMap.get("nickname");
            String headimgurl = (String)userInfoMap.get("headimgurl");

            //向数据库中插入一条记录
            member = new UcenterMember();
            member.setNickname(nickname);
            member.setOpenid(openid);
            member.setAvatar(headimgurl);
            memberService.save(member);

        }
        // 生成jwt
        String token = JwtUtils.getJwtToken(member.getId(),member.getNickname());
            return "redirect:http://172.20.10.10:80/?token="+token;//前端位置。 这个token是给前端的东西
    }

    /**
     * 微信登录功能
     * @return redirect一个新的地址
     */
    @GetMapping("login")
    public String genQrConnect() {

        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        //这个是回调地址。 当微信二维码扫完后，自动掉用这个地址。
        String url = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL;
        String appId = ConstantPropertiesUtil.WX_OPEN_APP_ID;
        String encodeUrl;
        try {
            encodeUrl = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new GuliException(false,99999,"登录异常");
        }

        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        String state = "freedom";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名

        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟

        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                appId,
                encodeUrl,
                state);

        return "redirect:" + qrcodeUrl;
    }

}
