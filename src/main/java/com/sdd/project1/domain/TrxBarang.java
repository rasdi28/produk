package com.sdd.project1.domain;

import java.math.BigDecimal;

public class TrxBarang {

	private Mbarang mbarang;
	
	private int qty;
	private BigDecimal total;
	
	
	public Mbarang getMbarang() {
		return mbarang;
	}
	public void setMbarang(Mbarang mbarang) {
		this.mbarang = mbarang;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
	
}
