package com.sdd.project1.domain;

import java.math.BigDecimal;

public class Trx {

	private Mproduct mproduct;
	private int qty;
	private BigDecimal totalprice;
	
	
	public Mproduct getMproduct() {
		return mproduct;
	}
	public void setMproduct(Mproduct mproduct) {
		this.mproduct = mproduct;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
}
