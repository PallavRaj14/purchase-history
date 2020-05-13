package com.wealthpark.purchasehistory.bean;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "Request parameter purchase product.")
public class PurchaseProductBean {

	@NotNull
	int purchaserId;
	
	@NotNull
	int productId;

	@ApiModelProperty(value = "Numeric id value of user", required = true, example="1", dataType="int")
	public int getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(int purchaserId) {
		this.purchaserId = purchaserId;
	}

	@ApiModelProperty(value = "Numeric id value of product", required = true, example="1", dataType="int")
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
