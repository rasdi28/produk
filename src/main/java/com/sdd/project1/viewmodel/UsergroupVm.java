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
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Separator;

import com.sdd.project1.domain.Musergroup;
import com.sdd.zk1.UserGroupVm;

public class UsergroupVm {

	@Wire
	private Grid grid;


	private Musergroup obj;

	private boolean isEdit;

	public Musergroup getObj() {
		return obj;
	}

	public void setObj(Musergroup obj) {
		this.obj = obj;
	}

	private List<Musergroup> objList;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		doReset();
		objList = new ArrayList<Musergroup>();
		grid.setRowRenderer(new RowRenderer<Musergroup>() {
			@Override
			public void render(Row row, final Musergroup data, int index) throws Exception {
				row.appendChild(new Label(String.valueOf(++index)));
				row.appendChild(new Label(data.getUsergroupcode()));
				row.appendChild(new Label(data.getUsergroupname()));
				row.appendChild(new Label(new SimpleDateFormat("dd-MM-yyyy:mm").format(data.getCreatedate())));

				Button btnEdit = new Button("Edit");
				btnEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						isEdit = true;
						obj = data;
						BindUtils.postNotifyChange(null, null, UsergroupVm.this, "obj");

					}
				});

				Button btnDel = new Button("delete");
				btnDel.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						objList.remove(data);
						doRefreshModel();

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
	
	public void doRefreshModel()
	{
		grid.setModel(new ListModelList<Musergroup>(objList));
	}
	
	public void doReset()
	{
		//obj ini itu variable dari m usergroup
		obj = new Musergroup();
		isEdit = false;
	}
	
	
	@Command
	@NotifyChange("obj")
	public void doSubmit()
	{
		obj.setCreatedate(new Date());
		if(isEdit) {
			Musergroup objUpdate = obj;
			objList.add(objUpdate);
			objList.remove(obj);
		} else {
			if(obj.getUsergroupcode() !=null && obj.getUsergroupname() !=null) {
				objList.add(obj);
			} else {
				Messagebox.show("harap isi data");
			}
		}
		
		doRefreshModel();
		doReset();
		
	}

}
