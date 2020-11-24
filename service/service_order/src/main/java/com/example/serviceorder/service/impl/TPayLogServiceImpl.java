package com.example.serviceorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.TOrder;
import com.example.commonutils.entity.TPayLog;
import com.example.serviceorder.dao.TPayLogDao;
import com.example.serviceorder.service.TOrderService;
import com.example.serviceorder.service.TPayLogService;
import com.example.serviceorder.utils.HttpClient;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付日志表(TPayLog)表服务实现类
 *
 * @author makejava
 * @since 2020-11-20 09:37:12
 */
@Service("tPayLogService")
public class TPayLogServiceImpl extends ServiceImpl<TPayLogDao, TPayLog> implements TPayLogService {

    @Resource
    private TOrderService orderService;

    @Override
    public Map<String, Object> createNative(String orderNo) {
        try {
            //根据订单id获取订单信息
            QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            TOrder order = orderService.getOne(wrapper);

            Map<String, String> m = new HashMap<>();
            //1、设置支付参数
            //appid：商家平台ID。在微信的平台上有
            //mch_id：商户ID。在微信的平台上有
            //body：商品描述。
            //nonce_str：随机字符串，UUID就好了。
            //out_trade_no：商户订单号
            //openid：用户标识。因为这边是用户已经登录成功了。所以在session中就能拿到。
            //spbill_create_ip：终端IP。这个可以从请求头中拿到
            //total_fee：支付金额。单位是分。
            //notify_url：通知地址。就是用户支付成功之后，微信访问你的哪个接口，跟你传递支付成功的相关信息。
            //trade_type：交易类型。这里我填JSAPI
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "172.25.201.23");
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify");
            m.put("trade_type", "NATIVE");

            //2、HTTPClient来根据URL访问第三方接口并且传递参数
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");

            //client设置参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml); //XML格式字符串转换为Map
            //4、封装返回结果集

            Map<String, Object> map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));

            //微信支付二维码2小时过期，可采取2小时未支付取消订单
            //redisTemplate.opsForValue().set(orderNo, map, 120, TimeUnit.MINUTES);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //获取订单id
        String orderNo = map.get("out_trade_no");
        //根据订单id查询订单信息
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder order = orderService.getOne(wrapper);

        if(order.getStatus()) return;
        order.setStatus(true);
        orderService.updateById(order);

        //记录支付日志
        TPayLog payLog=new TPayLog();
        payLog.setOrderNo(order.getOrderNo());//支付订单号
        payLog.setPayTime(new Date());
        payLog.setPayType(true);//支付类型
        payLog.setTotalFee(order.getTotalFee());//总金额(分)
        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(payLog);//插入到支付日志表
    }

    @Override
    public Map queryPayStatus(String orderNo) {
        try {
            //1、封装参数
            Map<String,String> m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            //6、转成Map
            //7、返回
            return WXPayUtil.xmlToMap(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
