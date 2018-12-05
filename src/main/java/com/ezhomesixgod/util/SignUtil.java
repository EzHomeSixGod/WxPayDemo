package com.ezhomesixgod.util;

import com.ezhomesixgod.model.RefundModel;
import com.ezhomesixgod.model.TransfersModel;
import com.ezhomesixgod.model.UnifiedorderModel;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信签名工具类
 *
 */
public class SignUtil {
      
	  /**
	   * 微信统一签名
	   * @param params
	   * @param mchKey 商户密钥
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
	  public static Map<String, String> createUnifiedSign(UnifiedorderModel request) {
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
	  
	   /**
	    * 创建微信企业退款签名map
	    * @param rdfund
	    * @return
	    */
	   public static Map<String, String> createRefundSign(RefundModel rdfund){
		   Map<String, String> map = new HashMap<>();
		   
		   map.put("appid", rdfund.getAppid());
		   map.put("mch_id", rdfund.getMch_id());
		   map.put("nonce_str", rdfund.getNonce_str());
		   map.put("sign", rdfund.getSign());
		   map.put("sign_type", rdfund.getSign_type());
		   map.put("out_trade_no", rdfund.getOut_trade_no());
		   map.put("out_refund_no", rdfund.getOut_refund_no());
		   map.put("total_fee", rdfund.getTotal_fee()+"");
		   map.put("refund_fee", rdfund.getRefund_fee()+"");
		   map.put("refund_desc", rdfund.getRefund_desc());
		   return map;
	   }
	   
	   /**
	    * 创建微信企业转账签名map
	    * @param transfers
	    * @return
	    */
	   public static Map<String, String> createtransfersSign(TransfersModel transfers){
		   Map<String, String> map = new HashMap<>();
		   
		   map.put("mch_appid", transfers.getMch_appid());
		   map.put("mchid", transfers.getMchid());
		   map.put("nonce_str", transfers.getNonce_str());
		   map.put("sign", transfers.getSign());
		   map.put("partner_trade_no", transfers.getPartner_trade_no());
		   map.put("openid", transfers.getOpenid());
		   map.put("check_name", transfers.getCheck_name());
		   map.put("re_user_name", transfers.getRe_user_name());
		   map.put("amount", transfers.getAmount()+"");
		   map.put("desc", transfers.getDesc());
		   map.put("spbill_create_ip", transfers.getSpbill_create_ip());
		   return map;
	   }
	   

	
}
