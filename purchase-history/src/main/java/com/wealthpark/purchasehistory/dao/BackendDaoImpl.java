package com.wealthpark.purchasehistory.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wealthpark.purchasehistory.schema.Product;
import com.wealthpark.purchasehistory.schema.PurchaseProduct;
import com.wealthpark.purchasehistory.schema.Purchaser;
/**
 * 
 * @author Pallav Raj
 * @Info DAO call for backend API
 *
 */
@Repository
@Transactional
public class BackendDaoImpl implements BackendDao {
	private final Logger logger = LoggerFactory.getLogger(BackendDaoImpl.class);

	@PersistenceContext(unitName = "backend-api")
	private EntityManager entityManager;

	public BackendDaoImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.wealthpark.purchasehistory.dao.BackendDao#customer(com.wealthpark.purchasehistory.schema.Purchaser)
	 */
	@Override
	public String customer(Purchaser purchaserRequestBean) {
		logger.info("Inside customer DAO");
		entityManager.persist(purchaserRequestBean);
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * @see com.wealthpark.purchasehistory.dao.BackendDao#product(com.wealthpark.purchasehistory.schema.Product)
	 */
	@Override
	public String product(Product productRequestBean) {
		logger.info("Inside product DAO");
		entityManager.persist(productRequestBean);
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * @see com.wealthpark.purchasehistory.dao.BackendDao#purchaseProduct(com.wealthpark.purchasehistory.schema.PurchaseProduct)
	 */
	@Override
	public String purchaseProduct(PurchaseProduct purchaseProduct) {
		logger.info("Inside purchase product DAO");
		Product product = entityManager.find(Product.class, purchaseProduct.getProductId());
		purchaseProduct.setName(product.getProdName());
		entityManager.persist(purchaseProduct);
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * @see com.wealthpark.purchasehistory.dao.BackendDao#getPurchaseHistory(int, java.sql.Timestamp, java.sql.Timestamp)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<String>> getPurchaseHistory(int purchaser_id, Timestamp start_date, Timestamp end_date) {
		logger.info("Inside purchase history DAO");
		Purchaser purchaser = entityManager.find(Purchaser.class, purchaser_id);
		List<Object[]> list = null;
		Map<String, List<String>> responseMap = null;
		List<String> productName = null;
		if (null != purchaser) {
			logger.debug("Executing query to get purchase history");
			Query query = entityManager.createNamedQuery("FetchByRequest").setParameter("purchaser_id", purchaser_id)
					.setParameter("start_date", start_date).setParameter("end_date", end_date);
			list = query.getResultList();
			responseMap = new HashedMap();
			responseJson(list, responseMap);
			return responseMap;
		} else {
			logger.error("Purchaser can not be null");
			productName= new ArrayList<>();
			responseMap= new HashedMap();
			productName.add("Purchaser can not be null");
			responseMap.put("err", productName);
			return responseMap;
		}

	}

	private void responseJson(List<Object[]> list, Map<String, List<String>> responseMap) {
		logger.info("Inside private method of purchase history DAO");
		List<String> productName;
		for (Object[] objects : list) {
			productName = new ArrayList<String>();
			String key = null;
			String value = null;
			for (int i = 0; i < objects.length; i++) {
				if (i == 0) {
					Timestamp ts = (Timestamp) objects[i];
					key = ts.toString().substring(0, 10);
				} else if (i == 1) {
					value = (String) objects[i];
					productName.add(value);
				}
			}
			if (!responseMap.containsKey(key)) {
				responseMap.put(key, productName);
			} else {
				responseMap.get(key).add(value);
			}
		}
	}

}
