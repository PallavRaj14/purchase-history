package com.wealthpark.purchasehistory.bean;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class PurchaseHistoryBean {

	private Timestamp date;
	private String prodName;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}



}
