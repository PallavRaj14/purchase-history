package com.wealthpark.purchasehistory.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Request parameter for register user.")
public class PurchaserRequestBean {

	@NotNull
	@NotBlank
	String userName;

	@ApiModelProperty(value = "Purchaser name", allowableValues = "String", required = true, example="Domnic")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
