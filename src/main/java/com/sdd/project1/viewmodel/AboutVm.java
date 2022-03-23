package com.sdd.project1.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;

import com.sdd.project1.domain.Mabout;

public class AboutVm {
	
	private Mabout objabout;
	
	private List<Mabout> objListAbout;
	
	
	
	@Wire
	private Combobox combobox;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
		objListAbout = new ArrayList<Mabout>();
		
		
		objabout = new Mabout();
		objabout.setHarga(120000);
	}
	
	public Mabout getObjabout() {
		return objabout;
	}

	public void setObjabout(Mabout objabout) {
		this.objabout = objabout;
	}

	
}

	