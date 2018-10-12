package com.ezhomesixgod;

import com.ezhomesixgod.wxconfig.WxPayConfig;
import com.ezhomesixgod.wxpayentity.UnifiedorderModelRequest;

import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{



    public static void main( String[] args ) {

        final int amount =100;
        final String openId ="xxxxxxxx";
        String orderNo = UUID.randomUUID().toString();

        UnifiedorderModelRequest model = new UnifiedorderModelRequest();
        model.setOpenid(openId);
        model.setOut_trade_no(orderNo);
        model.setTotal_fee(amount);
        model.setBody("备注信息");
        model.setTrade_type("JSAPI");
        model.setAppid(WxPayConfig.appId);
        model.setMch_id(WxPayConfig.mchId);
        model.setSpbill_create_ip("123.12.12.123");
        model.setNotify_url(WxPayConfig.notifyUrl);



    }
}
