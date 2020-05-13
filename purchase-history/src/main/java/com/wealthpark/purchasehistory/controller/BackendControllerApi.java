package com.wealthpark.purchasehistory.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wealthpark.purchasehistory.bean.ProductRequestBean;
import com.wealthpark.purchasehistory.bean.PurchaseProductBean;
import com.wealthpark.purchasehistory.bean.PurchaserRequestBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="purchase-history-service", description="End point for purchase and history")
public interface BackendControllerApi {
	
	@GetMapping(value="/")
	public String welcome();

	@ApiOperation(value="register purchaser", nickname="customer",
			notes="This API registers purchaser", tags= {"purchase-history-service"})
	@PostMapping(value = "/retail/purchaser")
	public String customer(@Valid @RequestBody PurchaserRequestBean purchaserRequestBean, BindingResult result);
	
	@ApiOperation(value="register product", nickname="product",
			notes="This API registers products", tags= {"purchase-history-service"})
	@PostMapping(value = "/retail/product")
	public String product(@Valid @RequestBody ProductRequestBean productRequestBean, BindingResult result);
	
	@ApiOperation(value="purchase product", nickname="purchaseProduct",
			notes="This API registers purchase of products", tags= {"purchase-history-service"})
	@PostMapping(value = "/purchaser-product")
	public String purchaseProduct(@Valid @RequestBody PurchaseProductBean purchaseProductBean, BindingResult result);
	
	@ApiOperation(value="purchase history", nickname="getPurchaseHistory",
			notes="This API returns purchase history", tags= {"purchase-history-service"})
	@GetMapping(value = "/retail/purchaser/{purchaser_id}/product", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, List<String>> getPurchaseHistory(
			@PathVariable(value = "purchaser_id", required = true) int purchaser_id,
			@RequestParam(value = "start_date", required = true) String start_date,
			@RequestParam(value = "end_date", required = true) String end_date) throws ParseException;
}
