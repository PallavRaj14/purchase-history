package com.wealthpark.purchasehistory.schema;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "purchase_product")
@Component
@NamedNativeQueries(@NamedNativeQuery(name ="FetchByRequest",
			query="SELECT sales_date, name FROM purchase_product WHERE purchaser_id = :purchaser_id AND sales_date BETWEEN :start_date AND :end_date GROUP BY sales_date"))
public class PurchaseProduct {

	@Id
	int purchaseProd_id;

	@Column(name = "purchaser_id")
	int purchaserId;

	@Column(name = "product_id")
	int productId;

	@Column(name = "sales_date")
	Timestamp salesDate;

	@Column(name = "NAME")
	String name;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "product_id", insertable = false, updatable = false, referencedColumnName = "product_id"),
			@JoinColumn(name = "name", insertable = false, updatable = false, referencedColumnName = "NAME") })

	private Product product;

	@ManyToOne
	@JoinColumn(name = "purchaser_id", insertable = false, updatable = false)
	private Purchaser purchaser;

	public int getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(int purchaserId) {
		this.purchaserId = purchaserId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Timestamp getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Timestamp salesDate) {
		this.salesDate = salesDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchaser getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(Purchaser purchaser) {
		this.purchaser = purchaser;
	}

	public int getPurchaseProd_id() {
		return purchaseProd_id;
	}

	public void setPurchaseProd_id(int purchaseProd_id) {
		this.purchaseProd_id = purchaseProd_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
