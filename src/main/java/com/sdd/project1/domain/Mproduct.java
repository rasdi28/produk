package com.sdd.project1.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Mproduct {
	
	private Integer mproductpk;
	private String productname;
	private BigDecimal productprice;
	private Integer productstock;
	
	@Id
	public Integer getMproductpk() {
		return mproductpk;
	}
	public void setMproductpk(Integer mproductpk) {
		this.mproductpk = mproductpk;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public BigDecimal getProductprice() {
		return productprice;
	}
	public void setProductprice(BigDecimal productprice) {
		this.productprice = productprice;
	}
	public Integer getProductstock() {
		return productstock;
	}
	public void setProductstock(Integer productstock) {
		this.productstock = productstock;
	}
	
	
}
