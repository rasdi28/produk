package com.sdd.project1.domain;

import java.util.Date;

public class Musergroup {
	
	private String usergroupcode;
	private String usergroupname;	
	private Date createdate;
	
	
	public String getUsergroupcode() {
		return usergroupcode;
	}
	public void setUsergroupcode(String usergroupcode) {
		this.usergroupcode = usergroupcode;
	}
	public String getUsergroupname() {
		return usergroupname;
	}
	public void setUsergroupname(String usergroupname) {
		this.usergroupname = usergroupname;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}	
}
