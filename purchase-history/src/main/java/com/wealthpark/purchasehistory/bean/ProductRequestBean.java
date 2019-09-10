package com.wealthpark.purchasehistory.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Request parameter for register product.")
public class ProductRequestBean {

	@NotNull
	@NotBlank
	String productName;

	@ApiModelProperty(value = "Product name", dataType = "String", required = true, example="Tomato")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
