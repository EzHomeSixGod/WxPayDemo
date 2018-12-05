package com.ezhomesixgod;

import com.ezhomesixgod.model.RefundModel;
import com.ezhomesixgod.model.UnifiedorderModel;

public class Test {
	

	public static void main(String[] args) {
	     //退款
	     RefundModel rdfund = new RefundModel();
		 rdfund.setOut_trade_no("545435454555354");
		 rdfund.setOut_refund_no("545435454555354");
		 rdfund.setTotal_fee(100);
		 rdfund.setRefund_fee(100);
		 rdfund.setRefund_desc("退款申请");
		 //Map<String, Object> res  = WxAplay.refundUtil(rdfund,req,2);
	}

}
