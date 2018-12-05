package com.ezhomesixgod.model;

/**
 * 统一下单支付实体
 *
 */
public class UnifiedorderModel {
	
	/**公众号appid*/
	private String appid;
	/**商户号*/
	private String mch_id;
	/**随机字符串*/
	private String nonce_str;
	/**签名*/
	private String sign;
	/**签名方式*/
	private String sign_type;
	
	
	/**商品描述：腾讯充值中心-QQ会员充值*/
	private String body;	
	/**商品详情**/
	private String detail;
	/**附加数据**/
	private String attach;
	/**支付订单号*/
	private String out_trade_no;
	/**总金额(分)*/
	private Integer total_fee;
	/**终端IP(8.8.8.8)*/
	private String spbill_create_ip;
	/**异步通知地址*/
	private String notify_url;
	/**交易类型**/
	private String trade_type;
	
	
	/**用户标识**/
	private String openid;
	/**商品id**/
	private String product_id;
	
		
	public UnifiedorderModel() {
		super();
	}
	
	
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}



	public String getAttach() {
		return attach;
	}



	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	
	
	

}
