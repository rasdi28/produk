package com.sdd.project1.viewmodel;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.sdd.project1.domain.Mproduct;
import com.sdd.project1.domain.Trx;

public class TransaksiVm {

	@Wire
	private Grid grid;
	private List<Mproduct> mproducts;
	private ListModelList<Mproduct> productModel;

	private List<Trx> listTrx;

	private Mproduct mproduct;
	private Integer qty;
	private BigDecimal totalprice;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		try {
			listTrx = new ArrayList<>();
			doSetProduct();

			grid.setRowRenderer(new RowRenderer<Trx>() {

				@Override
				public void render(Row row, final Trx data, int index) throws Exception {
					// TODO Auto-generated method stub
					row.appendChild(new Label(String.valueOf(++index)));
					row.appendChild(new Label(data.getMproduct().getProductname()));
					row.appendChild(new Label(NumberFormat.getInstance().format(data.getMproduct().getPrice())));
					row.appendChild(new Label(NumberFormat.getInstance().format(data.getQty())));
					row.appendChild(new Label(NumberFormat.getInstance().format(data.getTotalprice())));
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		doRefreshModel();
	}
	
	private void doRefreshModel() {
		grid.setModel(new ListModelList<>(listTrx));
	}

	private void doSetProduct() {

		mproducts = new ArrayList<>();
		Mproduct mproduct = new Mproduct();
		mproduct.setProductname("Laptop");
		mproduct.setPrice(new BigDecimal(7000000));
		mproducts.add(mproduct);

		mproduct = new Mproduct();
		mproduct.setProductname("Handphone");
		mproduct.setPrice(new BigDecimal(30000));
		mproducts.add(mproduct);
		
		
		
		productModel = new ListModelList<Mproduct>(mproducts);

	}

	@NotifyChange("totalprice")
	@Command
	public void doCal(@BindingParam("price") BigDecimal price, @BindingParam("qty") int qty) {
		totalprice = price.multiply(new BigDecimal(qty));
	}

	@Command
	@NotifyChange
	public void doCreate() {
		Trx trx = new Trx();
		trx.setMproduct(mproduct);
		trx.setQty(qty);
		trx.setTotalprice(totalprice);
		listTrx.add(trx);
		doRefreshModel();
	}

	public ListModelList<Mproduct> getProductModel() {
		return productModel;
	}

	public void setProductModel(ListModelList<Mproduct> productModel) {
		this.productModel = productModel;
	}

	public Mproduct getMproduct() {
		return mproduct;
	}

	public void setMproduct(Mproduct mproduct) {
		this.mproduct = mproduct;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

}
