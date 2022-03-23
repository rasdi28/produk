package com.sdd.project1.viewmodel;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Separator;

import com.sdd.project1.dao.MproductDAO;
import com.sdd.project1.domain.Mcoba;
import com.sdd.project1.domain.Mitem;
import com.sdd.project1.domain.Mproduct;

public class ProductVm {

	
	@Wire
	private Combobox combobox;
	

	@Wire
	private Grid grid;


	private List<Mproduct> objList;
	private Mproduct objproduct;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		doReset();
		
		objList = new ArrayList<Mproduct>();
		
		grid.setRowRenderer(new RowRenderer<Mproduct>() {

			@Override
			public void render(Row row, Mproduct data, int index) throws Exception {
				

				row.appendChild(new Label(String.valueOf(++index)));
				row.appendChild(new Label(data.getProductname()));
				row.appendChild(new Label(NumberFormat.getInstance().format(data.getProductstock())));
				row.appendChild(new Label(NumberFormat.getInstance().format(data.getProductprice())));	
				
				
			}
		});
	}
	
	public void doReset() {
		objproduct = new Mproduct();
		doRefreshModel();
	}

	public void doRefreshModel() {
		objList = new MproductDAO().listAll();
		grid.setModel(new ListModelList<Mproduct>(objList));
		
	}
	

}
