package com.ezhomesixgod.model;

import java.io.Serializable;

/**
 * 微信回调实体
 *
 */
public class CallBackWxModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5424613434748268634L;

	private String return_code;
	private String return_msg;

	public CallBackWxModel() {
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
	
	
	
}
