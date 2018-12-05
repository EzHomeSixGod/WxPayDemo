package com.ezhomesixgod;

import com.alibaba.fastjson.JSONObject;
import com.ezhomesixgod.config.BasicInfo;
import com.ezhomesixgod.model.*;
import com.ezhomesixgod.util.HttpUtil;
import com.ezhomesixgod.util.PayUtil;
import com.ezhomesixgod.util.SignUtil;
import com.ezhomesixgod.util.XMLUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * 微信统一支付服务
 *
 */
public class WxPay{
    
	/******log record******/
	protected static Logger log = Logger.getLogger(WxPay.class);

	/**
	 * APP微信支付
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static UnifiedorderResponse payApp(HttpServletRequest req,
											  UnifiedorderModel model) throws Exception {

			model.setNonce_str(PayUtil.getRandomStr());
			model.setSign(SignUtil.sign(SignUtil.createUnifiedSign(model),
					BasicInfo.APP_MchKey));
			try{
				
			  XMLUtil xmlUtil= new XMLUtil();
			  xmlUtil.xstream().alias("xml", model.getClass());
			
			  String xml = xmlUtil.xstream().toXML(model);
			  String response = PayUtil.ssl(BasicInfo.unifiedordersurl, xml,
					  req,BasicInfo.APP_MchId);
			  
		      UnifiedorderResponse ret = (UnifiedorderResponse) 
		 		XMLUtil.fromXML(response,UnifiedorderResponse.class); 
		      
		      System.out.println("-------------------");
		      System.out.println(JSONObject.toJSONString(ret));

			  if("SUCCESS".equals(ret.getResult_code())){
				  
				  //再次签名
				  Map<String, String> finalpackage = new TreeMap<String, String>();
				  String timestamp = (System.currentTimeMillis()/1000)+"";
				  String noncestr = PayUtil.getRandomStr();
			     
			      finalpackage.put("appid", BasicInfo.APP_AppID); 
			      finalpackage.put("timestamp", timestamp);  
			      finalpackage.put("noncestr", noncestr);  		
			      finalpackage.put("prepayid", ret.getPrepay_id());
			      finalpackage.put("package", "Sign=WXPay"); 
			      finalpackage.put("partnerid",BasicInfo.APP_MchId); 
		
			      String sign = SignUtil.sign(finalpackage, BasicInfo.APP_MchKey);
			      	      
			      ret.setSign(sign);
			      ret.setNonce_str(noncestr);
			      ret.setTimestamp(timestamp);
				  		  
				  return ret;
			  }else{
				
				 log.error("微信下单失败》》"+"错误码:"+ret.getReturn_code()+"  ;"
				 	              	+ "描述:"+ret.getReturn_msg());
				 return ret;
			  }
			}catch (Exception e) {
				  log.error("微信下单异常》》"+e);
			      throw new RuntimeException();
			}

     }

	/**
	 * 微信退款
	 * @param rdfund
	 * @param req
	 * @param type 1-公众号  2-小程序  3-app
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object>  refundUtil(RefundModel rdfund
			, HttpServletRequest req,Integer type) throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		//设置支付账户信息
		String MchKey = ""; //商户号秘钥
		String MchId = ""; //商户号id
		rdfund.setAppid(BasicInfo.APP_AppID);
		rdfund.setMch_id(BasicInfo.APP_MchId);
		MchKey = BasicInfo.APP_MchKey;
		MchId = BasicInfo.APP_MchId;

		rdfund.setNonce_str(PayUtil.getRandomStr());
		rdfund.setSign_type("MD5");
		rdfund.setSign(SignUtil.sign(SignUtil.createRefundSign(rdfund),MchKey));

		try{
			XMLUtil xmlUtil= new XMLUtil();
			xmlUtil.xstream().alias("xml", rdfund.getClass());

			String xml = xmlUtil.xstream().toXML(rdfund);
			String response = PayUtil.ssl(BasicInfo.refundurl, xml,req,MchId);
			System.out.println(response);
			Map<String, String> map = xmlUtil.parseXml(response);
			if("SUCCESS".equals(map.get("result_code"))){
				mp.put("stu", true);
				return mp;
			}else{
				mp.put("stu", false);
				mp.put("errMsg", map.get("return_msg"));
				mp.put("errDes", map.get("err_code_des"));
				log.error("微信退款失败》》"+"错误码:"+map.get("return_msg")+"  ;"
						+ "描述:"+map.get("err_code_des"));
				return mp;
			}
		}catch (Exception e) {
			log.error("微信退款异常》》"+e);
			throw new RuntimeException();
		}
	}


	/**
	 * 企业转账接口
	 * @param transfer
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object>  transfersUtil(TransfersModel transfer
			, HttpServletRequest req) throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		transfer.setMch_appid(BasicInfo.appID);
		transfer.setMchid(BasicInfo.MchId);
		transfer.setNonce_str(PayUtil.getRandomStr());
		transfer.setCheck_name("NO_CHECK");
		transfer.setSpbill_create_ip("127.0.0.1");
		transfer.setSign(SignUtil.sign(SignUtil.createtransfersSign(transfer),BasicInfo.MchKey));

		try{
			XMLUtil xmlUtil= new XMLUtil();
			xmlUtil.xstream().alias("xml", transfer.getClass());

			String xml = xmlUtil.xstream().toXML(transfer);
			String response = PayUtil.ssl(BasicInfo.transfersurl, xml,req,BasicInfo.MchKey);
			System.out.println(response);
			Map<String, String> map = xmlUtil.parseXml(response);

			if("SUCCESS".equals(map.get("result_code"))){
				mp.put("stu", true);
				return mp;
			}else{
				mp.put("stu", false);
				mp.put("errMsg", map.get("return_msg"));
				mp.put("errDes", map.get("err_code_des"));
				log.error("企业转账失败》》"+"错误码:"+map.get("return_msg")+"  ;"
						+ "描述:"+map.get("err_code_des"));
				return mp;
			}
		}catch (Exception e) {
			log.error("企业转账异常》》"+e);
			throw new RuntimeException();
		}
	}



}
