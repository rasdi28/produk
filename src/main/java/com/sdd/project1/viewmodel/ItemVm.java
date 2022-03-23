package com.sdd.project1.viewmodel;

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

import com.sdd.project1.domain.Mcoba;
import com.sdd.project1.domain.Mitem;

public class ItemVm {

	private Mitem obj;
	private Mcoba objnew;
	
	private boolean isEdit;
	private String jmlh ;
	private String jmlhstok;
	
	@Wire
	private Combobox combobox;
	

	@Wire
	private Grid grid;
	private int no;
	
	private int totalstock;

	private List<Mitem> objItemList;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		doReset();
		setObjnew(new Mcoba());
		objItemList = new ArrayList<Mitem>();
		
		grid.setRowRenderer(new RowRenderer<Mitem>() {

			@Override
			public void render(Row row, Mitem data, int index) throws Exception {
				// TODO Auto-generated method stub

				row.appendChild(new Label(String.valueOf(++index)));
				row.appendChild(new Label(data.getItemnama()));
				row.appendChild(new Label(String.valueOf(data.getItemstock())));
				row.appendChild(new Label(data.getItemjenis()));
				row.appendChild(new Label(new SimpleDateFormat("dd-MM-yyyy").format(data.getCreatedate())));
				

				Button btnEdit = new Button("Edit");
				btnEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub

						isEdit = true;
						obj = data;
						BindUtils.postNotifyChange(null, null, ItemVm.this, "obj");

					}

				});

				Button btnDel = new Button("delete");
				btnDel.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
;
					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						
						objItemList.remove(data);
						doRefreshModel();
						totalstock -= data.getItemstock();
						jmlhstok = String.valueOf(totalstock);
						jmlh = String.valueOf(objItemList.size());
						//bind util ini digunakan untuk ngasih tau zull kalau di event listener
						BindUtils.postNotifyChange(null, null, ItemVm.this, "*");
						
				

					}
				});

				Hbox hbox = new Hbox();
				hbox.appendChild(btnEdit);
				hbox.appendChild(new Separator("horizontal"));
				hbox.appendChild(btnDel);
				row.appendChild(hbox);
			}
		});
	}

	public void doRefreshModel() {
		
		grid.setModel(new ListModelList<Mitem>(objItemList));
		jmlh = String.valueOf(objItemList.size());

		
	}
	


	public void doReset() {
		obj = new Mitem();
		isEdit = false;
	}

	@Command
	@NotifyChange("*")
	public void doSubmit()
	{
		totalstock += obj.getItemstock();
		jmlhstok = String.valueOf(totalstock);
		obj.setCreatedate(new Date());
		if(isEdit) {
			Mitem objUpdate = obj;
			objItemList.add(objUpdate);
			objItemList.remove(obj);
		} else {
			if (obj.getItemnama() !=null && obj.getItemjenis() !=null && obj.getItemstock() !=0 ) {
				objItemList.add(obj);
			} else {
				Messagebox.show("harap isi data");
				
			}
		}
		
		jmlh = String.valueOf(objItemList.size());
		doRefreshModel();
		doReset();
	}


	public String getJmlh() {
		return jmlh;
	}

	public void setJmlh(String jmlh) {
		this.jmlh = jmlh;
	}
	
	public Mitem getObj() {
		return obj;
	}

	public void setObj(Mitem obj) {
		this.obj = obj;
	}

	public String getJmlhstok() {
		return jmlhstok;
	}

	public void setJmlhstok(String jmlhstok) {
		this.jmlhstok = jmlhstok;
	}

	public Mcoba getObjnew() {
		return objnew;
	}

	public void setObjnew(Mcoba objnew) {
		this.objnew = objnew;
	}

}
