package com.sdd.project1.viewmodel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Separator;

public class Loginvm {

	//HARDCODE
	private static final String USERID = "rasdi";
	private static final String PASSWORD = "rasdi123";
	
	
	int counter;
	//VARIABLE BINDING KE ZULL
	private String userId, password;
	
	@Wire
	private Groupbox gbMsg;
	
	
	@Wire
	private Button btnClear;
	
	@AfterCompose
	public void afterCompose (@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		System.out.println("running login vm");
		btnClear.setDisabled(true);
	}
	
	//method
	
	@Command
	public void doLogin()
	{
		btnClear.setDisabled(false);
		
		if (userId !=null && password !=null && userId.trim().length() >0 && password.trim().length() >0) {
			if (loginChecker(userId, password)) {
				Executions.sendRedirect("/view/livingroom.zul");
			}
			Messagebox.show("login Failed");
		} else {
			counter ++ ;
			//buat konstructor
			Label msg = new Label("Isi dahulu nilainya");
			gbMsg.appendChild(msg);
			gbMsg.appendChild(new Separator());
			doTitle();
		}
	}
	
	@Command
	public void doClearMsg()
	{
		btnClear.setDisabled(false);
		counter = 0;
		gbMsg.getChildren().clear();
		doTitle();
		btnClear.setDisabled(true);
	}
	
	
	public void doTitle()
	{
		gbMsg.setTitle("message ("+ counter +")");
	}
	
	private boolean loginChecker( String id, String pw) {
		//
		boolean isValid = false;
		if(id.equals(USERID) && pw.equals(PASSWORD)) {
			isValid = true;
		}
		return isValid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
