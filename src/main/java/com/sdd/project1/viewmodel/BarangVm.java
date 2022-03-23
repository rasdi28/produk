package com.sdd.project1.viewmodel;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Label;

import com.sdd.project1.domain.Mbarang;
import com.sdd.project1.domain.TrxBarang;

public class BarangVm {

	private List<Mbarang> mbarangs;
	private Mbarang mbarang;
	private Integer qty;
	private BigDecimal total;
	
	private List<TrxBarang> listTrxbarang;
	
	@Wire
	private Grid grid;



	private ListModelList<Mbarang> barangModel;
	
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
		listTrxbarang = new ArrayList<>();
		doSetBarang();
		
		grid.setRowRenderer(new RowRenderer<TrxBarang>() {

			@Override
			public void render(Row row, final TrxBarang data, int index) throws Exception {
			
				row.appendChild(new Label(String.valueOf(++index)));
				row.appendChild(new Label(data.getMbarang().getNamabarang()));
				row.appendChild(new Label(data.getMbarang().getJenis()));
				row.appendChild(new Label("Rp. "+NumberFormat.getInstance().format(data.getMbarang().getPrice())));
				row.appendChild(new Label(String.valueOf(data.getQty())));
				row.appendChild(new Label("Rp. "+NumberFormat.getInstance().format(data.getTotal())));
				
				
			}
		});
		doRefreshModel();
		
	}
	
	private void doRefreshModel()
	{
		grid.setModel(new ListModelList<>(listTrxbarang));
	}
	
	private void doSetBarang() {
		
		mbarangs = new ArrayList<>();
		Mbarang mbarang = new Mbarang();
		mbarang.setNamabarang("Buku");
		mbarang.setPrice(new BigDecimal(20000));
		mbarang.setJenis("ATK");
		mbarangs.add(mbarang);
		
		mbarang = new Mbarang();
		mbarang.setNamabarang("pencil");
		mbarang.setPrice(new BigDecimal(30000));
		mbarang.setJenis("ATK");
		mbarangs.add(mbarang);
		
		mbarang = new Mbarang();
		mbarang.setNamabarang("headset");
		mbarang.setPrice(new BigDecimal(50000000));
		mbarang.setJenis("Elektronik");
		mbarangs.add(mbarang);
		
		barangModel = new ListModelList<>(mbarangs);
		
	}
	
	@Command
	@NotifyChange("total")
	public void doCal(@BindingParam("price") BigDecimal price, @BindingParam("qty") int qty) {
		total = price.multiply(new BigDecimal(qty));
	}
	
	@Command
	@NotifyChange
	public void doCreate() {
		TrxBarang trxbarang = new TrxBarang();
		trxbarang.setMbarang(mbarang);
		trxbarang.setQty(qty);
		trxbarang.setTotal(total);
		listTrxbarang.add(trxbarang);
		doRefreshModel();
	}
	
	

	public List<Mbarang> getMbarangs() {
		return mbarangs;
	}

	public void setMbarangs(List<Mbarang> mbarangs) {
		this.mbarangs = mbarangs;
	}



	public Integer getQty() {
		return qty;
	}



	public void setQty(Integer qty) {
		this.qty = qty;
	}



	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
		this.total = total;
	}



	
	
	public Mbarang getMbarang() {
		return mbarang;
	}



	public void setMbarang(Mbarang mbarang) {
		this.mbarang = mbarang;
	}



	public ListModelList<Mbarang> getBarangModel() {
		return barangModel;
	}



	public void setBarangModel(ListModelList<Mbarang> barangModel) {
		this.barangModel = barangModel;
	}

	public List<TrxBarang> getListTrxbarang() {
		return listTrxbarang;
	}

	public void setListTrxbarang(List<TrxBarang> listTrxbarang) {
		this.listTrxbarang = listTrxbarang;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	
	
}
