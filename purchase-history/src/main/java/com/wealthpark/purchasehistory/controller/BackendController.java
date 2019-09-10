package com.wealthpark.purchasehistory.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.wealthpark.purchasehistory.bean.ProductRequestBean;
import com.wealthpark.purchasehistory.bean.PurchaseProductBean;
import com.wealthpark.purchasehistory.bean.PurchaserRequestBean;
import com.wealthpark.purchasehistory.exception.ProductPurchaseException;
import com.wealthpark.purchasehistory.service.BackendService;

/**
 * 
 * @author Pallav Raj
 * @Info Backend API controller for maintaining customer and purchase history.
 *
 */

@RestController
@RequestScope
public class BackendController implements BackendControllerApi {

	private final Logger logger = LoggerFactory.getLogger(BackendController.class);

	@Autowired
	private BackendService backendService;

	/*
	 * (non-Javadoc)
	 * @see com.wealthpark.purchasehistory.controller.BackendControllerApi#customer(com.wealthpark.purchasehistory.bean.PurchaserRequestBean, org.springframework.validation.BindingResult)
	 */
	@Override
	public String customer(@Valid @RequestBody PurchaserRequestBean purchaserRequestBean, BindingResult result) {
		logger.info("Purchase endpoint initilized");
		String reponseValue = null;
		try {
			if (result.hasErrors()) {
				logger.error("Username can not be null or blank");
				return "Username can not be null or blank";
			}

			reponseValue = backendService.customer(purchaserRequestBean.getUserName());
			logger.debug(reponseValue);
		} catch (Exception e) {
			logger.error("purchaser can not be registered");
			return "purchaser can not be registered";
		}

		return reponseValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.wealthpark.purchasehistory.controller.BackendControllerApi#product(com.wealthpark.purchasehistory.bean.ProductRequestBean, org.springframework.validation.BindingResult)
	 */
	@Override
	public String product(@Valid @RequestBody ProductRequestBean productRequestBean, BindingResult result) {
		logger.info("Product endpoint initilized");
		String reponseValue = null;
		try {
			if (result.hasErrors()) {
				logger.error("product name can not be null or blank");
				return "product name can not be null or blank";
			}

			reponseValue = backendService.product(productRequestBean.getProductName());
			logger.debug(reponseValue);
		} catch (Exception e) {
			logger.error("product can not be registered");
			return "product can not be registered";
		}
		return reponseValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.wealthpark.purchasehistory.controller.BackendControllerApi#purchaseProduct(com.wealthpark.purchasehistory.bean.PurchaseProductBean, org.springframework.validation.BindingResult)
	 */
	@Override
	public String purchaseProduct(@Valid @RequestBody PurchaseProductBean purchaseProductBean, BindingResult result) {
		logger.info("purchaser-product endpoint initilized");
		String responseValue = null;
		try {
			if (result.hasErrors()) {
				logger.error("username and product id can not be null or blank");
				return "username and product id can not be null or blank";
			}
			responseValue = backendService.purchaseProduct(purchaseProductBean);
		} catch (Exception e) {
			return "Something wrong has happened with purchase product";
		}
		return responseValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.wealthpark.purchasehistory.controller.BackendControllerApi#getPurchaseHistory(int, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public @ResponseBody Map<String, List<String>> getPurchaseHistory(
			@PathVariable(value = "purchaser_id", required = true) int purchaser_id,
			@RequestParam(value = "start_date", required = true) String start_date,
			@RequestParam(value = "end_date", required = true) String end_date) throws ParseException {
		logger.info("Getting purchasing history");

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = formatter.parse(start_date);
		Date endDate = formatter.parse(end_date);
		Map<String, List<String>> purchaseHistory = null;

		Timestamp startStampDate = new Timestamp(startDate.getTime());
		Timestamp endStampDate = new Timestamp(endDate.getTime());

		try {
			if (purchaser_id > 0 && start_date != null && !start_date.equalsIgnoreCase("") && end_date != null
					&& !end_date.equalsIgnoreCase("")) {
				purchaseHistory = backendService.getPurchaseHistory(purchaser_id, startStampDate, endStampDate);
				logger.debug(purchaseHistory.toString());
			} else {
				logger.error("Some value is null");
				throw new ProductPurchaseException();
			}
		} catch (ProductPurchaseException ppe) {
			logger.error("Some value is null");
			List<String> productName = new ArrayList<>();
			purchaseHistory = new HashedMap();
			productName.add("Some value is null");
			purchaseHistory.put("err", productName);
			return purchaseHistory;
		}
		return purchaseHistory;

	}
}
