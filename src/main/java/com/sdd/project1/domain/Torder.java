package com.sdd.project1.domain;

import java.math.BigInteger;
import java.sql.Date;

public class Torder {
	private Integer torderpk;
	private int orderqty;
	private Date ordertime;
	private BigInteger totalprice;
	private Mproduct mproduct;
	
	public Integer getTorderpk() {
		return torderpk;
	}
	public void setTorderpk(Integer torderpk) {
		this.torderpk = torderpk;
	}
	public int getOrderqty() {
		return orderqty;
	}
	public void setOrderqty(int orderqty) {
		this.orderqty = orderqty;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public BigInteger getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigInteger totalprice) {
		this.totalprice = totalprice;
	}
	public Mproduct getMproduct() {
		return mproduct;
	}
	public void setMproduct(Mproduct mproduct) {
		this.mproduct = mproduct;
	}
	
}
