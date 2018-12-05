package com.ezhomesixgod.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


/**
 * 微信回调实体

 */
@Root(name = "xml", strict = false)
public class PayResponse {


	@Element(name = "return_code")
    private String return_code;

    @Element(name = "return_msg", required = false)
    private String return_msg;

    /** 以下字段在return_code为SUCCESS的时候有返回. */
    @Element(name = "appid", required = false)
    private String appid;

    @Element(name = "mch_id", required = false)
    private String mch_id;

    @Element(name = "device_info", required = false)
    private String device_info;

    @Element(name = "nonce_str", required = false)
    private String nonce_str;

    @Element(name = "sign", required = false)
    private String sign;

    @Element(name = "result_code", required = false)
    private String result_code;

    @Element(name = "err_code", required = false)
    private String err_code;

    @Element(name = "err_code_des", required = false)
    private String err_code_des;

    @Element(name = "openid", required = false)
    private String openid;

    @Element(name = "is_subscribe", required = false)
    private String is_subscribe;

    @Element(name = "trade_type", required = false)
    private String trade_type;

    @Element(name = "bank_type", required = false)
    private String bank_type;

    @Element(name = "total_fee", required = false)
    private Integer total_fee;

    @Element(name = "fee_type", required = false)
    private String fee_type;

    @Element(name = "cash_fee", required = false)
    private String cash_fee;

    @Element(name = "cash_fee_type", required = false)
    private String cash_fee_type;

    @Element(name = "coupon_fee", required = false)
    private String coupon_fee;

    @Element(name = "coupon_count", required = false)
    private String coupon_count;

    @Element(name = "transaction_id", required = false)
    private String transaction_id;

    @Element(name = "out_trade_no", required = false)
    private String out_trade_no;

    @Element(name = "attach", required = false)
    private String attach;

    @Element(name = "time_end", required = false)
    private String time_end;

    
    
  
	public PayResponse() {
		super();
	}




	public String getReturn_code() {
		return return_code;
	}




	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}




	public String getReturn_msg() {
		return return_msg;
	}




	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
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




	public String getDevice_info() {
		return device_info;
	}




	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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




	public String getResult_code() {
		return result_code;
	}




	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}




	public String getErr_code() {
		return err_code;
	}




	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}




	public String getErr_code_des() {
		return err_code_des;
	}




	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}




	public String getOpenid() {
		return openid;
	}




	public void setOpenid(String openid) {
		this.openid = openid;
	}




	public String getIs_subscribe() {
		return is_subscribe;
	}




	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}




	public String getTrade_type() {
		return trade_type;
	}




	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}




	public String getBank_type() {
		return bank_type;
	}




	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}




	public Integer getTotal_fee() {
		return total_fee;
	}




	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}




	public String getFee_type() {
		return fee_type;
	}




	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}




	public String getCash_fee() {
		return cash_fee;
	}




	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}




	public String getCash_fee_type() {
		return cash_fee_type;
	}




	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}




	public String getCoupon_fee() {
		return coupon_fee;
	}




	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}




	public String getCoupon_count() {
		return coupon_count;
	}




	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
	}




	public String getTransaction_id() {
		return transaction_id;
	}




	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}




	public String getOut_trade_no() {
		return out_trade_no;
	}




	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}




	public String getAttach() {
		return attach;
	}




	public void setAttach(String attach) {
		this.attach = attach;
	}




	public String getTime_end() {
		return time_end;
	}




	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}


    
    


}
