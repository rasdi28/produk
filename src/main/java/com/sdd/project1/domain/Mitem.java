package com.sdd.project1.domain;

import java.util.Date;

public class Mitem {
	
	private String itemnama;
	private int itemstock;
	private String itemjenis;
	private Date createdate;
	
	
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getItemnama() {
		return itemnama;
	}
	public void setItemnama(String itemnama) {
		this.itemnama = itemnama;
	}

	public int getItemstock() {
		return itemstock;
	}
	public void setItemstock(int itemstock) {
		this.itemstock = itemstock;
	}
	public String getItemjenis() {
		return itemjenis;
	}
	public void setItemjenis(String itemjenis) {
		this.itemjenis = itemjenis;
	}
	
	

}
