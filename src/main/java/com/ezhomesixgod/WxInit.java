package com.ezhomesixgod;


import com.ezhomesixgod.config.BasicInfo;
import com.ezhomesixgod.model.UnifiedorderModel;

/**
 * 微信支付相关初始化实例配置
 *
 */
public class WxInit {

	/**APP请求实体**/
	private static UnifiedorderModel AppModel = null;


	/**
	 * 获取app支付实体
	 * @return
	 */
	public static UnifiedorderModel getInstanceAppModel() {
		if(AppModel==null) {
			AppModel = new UnifiedorderModel();
			AppModel = new UnifiedorderModel();
			//异步通知地址
			AppModel.setNotify_url(BasicInfo.NotifyUrl);
	         //app应用appId
			AppModel.setAppid(BasicInfo.APP_AppID);
	         //商户号
			AppModel.setMch_id(BasicInfo.APP_MchId);
			//请求方式
			AppModel.setTrade_type("APP");
			//加密方式
			AppModel.setSign_type("MD5"); 
			AppModel.setSpbill_create_ip("8.8.8.8");
	        return AppModel;
		}else {
			return AppModel;
		}
	}
	

	
}
