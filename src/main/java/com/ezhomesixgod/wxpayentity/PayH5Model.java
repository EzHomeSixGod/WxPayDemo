package com.ezhomesixgod.wxpayentity;

/**
 * @author renyang
 * @description
 * @createTime 2018-10-12 11:38
 */
public class PayH5Model {

    private String appId;

    private String timeStamp;

    private String nonceStr;

    private String packAge;

    private String signType;

    private String paySign;

    private String return_code;

    private String result_code;

    private String return_msg;


    public PayH5Model() {
        super();
    }



    public PayH5Model(String return_code, String return_msg) {
        super();
        this.return_code = return_code;
        this.return_msg = return_msg;
    }



    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackAge() {
        return packAge;
    }

    public void setPackAge(String packAge) {
        this.packAge = packAge;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }



}
