package com.wealthpark.purchasehistory.bean;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class PurchaseHistoryRequestBean {

	@NotNull
	@NotBlank
	int purchaser_id;
	
	@NotNull
	@NotBlank
	Date startDate;
	
	@NotNull
	@NotBlank
	Date endDate;

	public int getPurchaser_id() {
		return purchaser_id;
	}

	public void setPurchaser_id(int purchaser_id) {
		this.purchaser_id = purchaser_id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
