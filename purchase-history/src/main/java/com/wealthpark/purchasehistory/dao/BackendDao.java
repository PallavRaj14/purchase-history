package com.wealthpark.purchasehistory.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.wealthpark.purchasehistory.schema.Product;
import com.wealthpark.purchasehistory.schema.PurchaseProduct;
import com.wealthpark.purchasehistory.schema.Purchaser;

public interface BackendDao {
	public String customer(Purchaser purchaserRequestBean);

	public String product(Product purchaser);

	public String purchaseProduct(PurchaseProduct purchaseProduct);
	
	public Map<String, List<String>>  getPurchaseHistory(int purchaser_id, Timestamp start_date, Timestamp end_date);
}
