package com.wealthpark.purchasehistory.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wealthpark.purchasehistory.bean.PurchaseProductBean;
import com.wealthpark.purchasehistory.dao.BackendDao;
import com.wealthpark.purchasehistory.schema.Product;
import com.wealthpark.purchasehistory.schema.PurchaseProduct;
import com.wealthpark.purchasehistory.schema.Purchaser;

@Service
public class BackendServiceImpl implements BackendService {

	@Autowired
	private BackendDao backendDao;

	@Autowired
	private Purchaser purchaser;
	
	@Autowired
	private Product product;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private PurchaseProduct purchaseProduct;
	
	@Override
	public String customer(String purchaserRequestBean) {
		purchaser.setUserName(purchaserRequestBean);
		String responseValue = backendDao.customer(purchaser);
		return responseValue;
	}
	
	@Override
	public String product(String productRequestBean) {
		product.setProdName(productRequestBean);
		String responseValue = backendDao.product(product);
		return responseValue;
	}

	@Override
	public String purchaseProduct(PurchaseProductBean purchaseProductBean) {
		mapper.map(purchaseProductBean, purchaseProduct);
		String responseValue= backendDao.purchaseProduct(purchaseProduct);
		return responseValue;
	}

	@Override
	public Map<String, List<String>> getPurchaseHistory(int purchaser_id, Timestamp start_date, Timestamp end_date) {
		Map<String, List<String>> purchaseHistoryBean = backendDao.getPurchaseHistory(purchaser_id, start_date, end_date);
		return purchaseHistoryBean;
	}
	
}
