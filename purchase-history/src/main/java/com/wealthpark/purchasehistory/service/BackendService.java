package com.wealthpark.purchasehistory.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.wealthpark.purchasehistory.bean.PurchaseProductBean;

public interface BackendService {
	public String customer(String string);

	public String product(String productRequestBean);
	
	public String purchaseProduct(PurchaseProductBean purchaseProductBean);
	
	public Map<String, List<String>>  getPurchaseHistory(int purchaser_id, Timestamp start_date, Timestamp end_date);
}
