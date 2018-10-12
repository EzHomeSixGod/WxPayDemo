package com.ezhomesixgod.wxpayutil;

import com.ezhomesixgod.wxconfig.WxPayConfig;
import com.ezhomesixgod.wxpayentity.PayH5Model;
import com.ezhomesixgod.wxpayentity.UnifiedorderModelRequest;
import com.ezhomesixgod.wxpayentity.UnifiedorderModelResponse;
import com.ezhomesixgod.wxpayentity.UnifiedorderResponse;
import com.ezhomesixgod.xmlutil.XmlUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author renyang
 * @description
 * @createTime 2018-10-12 10:47
 */
public class WxPay {

    private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHI"
            + "JKLMNOPQRSTUVWXYZ0123456789";

    private static final java.util.Random RANDOM = new java.util.Random();

    public static PayH5Model pay(UnifiedorderModelRequest model){
        model.setNonce_str(getRandomStr());
        model.setSign(sign(createUnifiedSign(model),WxPayConfig.MchKey));

        XmlUtil xmlUtil= new XmlUtil();
        xmlUtil.xstream().alias("xml", model.getClass());
        String xml = xmlUtil.xstream().toXML(model);
        //发起支付请求
        String response = PayUtil.ssl(WxPayConfig.notifyUrl, xml);
        UnifiedorderResponse ret = (UnifiedorderResponse)
                XmlUtil.fromXML(response,UnifiedorderResponse.class);

        if("SUCCESS".equals(ret.getResult_code())){
            PayH5Model res = buildPayH5Model(ret);
            return res;
        }else{

            System.out.println("微信下单失败》》"+"错误码:"+ret.getReturn_code()+"  ;"
                    + "描述:"+ret.getReturn_msg());

            return new PayH5Model(ret.getReturn_code(), ret.getReturn_msg());
        }
    }



    /**
     * 微信统一签名
     * @param params
     * @return
     */
    public static String sign(Map<String, String> params,String mchKey) {
        SortedMap<String, String> sortedMap = new TreeMap<>(params);

        StringBuilder toSign = new StringBuilder();
        for (String key : sortedMap.keySet()) {
            String value = params.get(key);
            if (value!=null && !"".equals(value) && !"sign".equals(key)
                    && !"key".equals(key)) {
                toSign.append(key).append("=").append(value).append("&");
            }
        }

        toSign.append("key=").append(mchKey);
        return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }



    /**
     * 创建统一下单签名map
     * @param request
     * @return
     */
    public static Map<String, String> createUnifiedSign(UnifiedorderModelRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", request.getAppid());
        map.put("mch_id", request.getMch_id());
        map.put("nonce_str", request.getNonce_str());
        map.put("sign", request.getSign());
        map.put("sign_type", request.getSign_type());
        map.put("attach", request.getAttach());
        map.put("body", request.getBody());
        map.put("detail", request.getDetail());
        map.put("notify_url", request.getNotify_url());
        map.put("openid", request.getOpenid());
        map.put("out_trade_no", request.getOut_trade_no());
        map.put("spbill_create_ip", request.getSpbill_create_ip());
        map.put("total_fee", String.valueOf(request.getTotal_fee()));
        map.put("trade_type", request.getTrade_type());
        return map;
    }


    //随机字符串
    public static String getRandomStr(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
        }
        return sb.toString();
    }


    public static PayH5Model buildPayH5Model(UnifiedorderResponse response) {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = getRandomStr();
        String packAge = "prepay_id=" + response.getPrepay_id();
        String signType = "MD5";

        //先构造要签名的map
        Map<String, String> map = new HashMap<>();
        map.put("appId", response.getAppid());
        map.put("timeStamp", timeStamp);
        map.put("nonceStr", nonceStr);
        map.put("package", packAge);
        map.put("signType", signType);

        PayH5Model model = new PayH5Model();
        model.setAppId(response.getAppid());
        model.setTimeStamp(timeStamp);
        model.setNonceStr(nonceStr);
        model.setPackAge(packAge);
        model.setSignType(signType);
        model.setPaySign(sign(map, WxPayConfig.MchKey));

        return model;
    }

}
