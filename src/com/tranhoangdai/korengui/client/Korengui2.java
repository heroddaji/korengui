package com.tranhoangdai.korengui.client;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.TabPane;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.ui.AboutBox;
import com.tranhoangdai.korengui.client.ui.TablePanel;
import com.tranhoangdai.korengui.client.ui.SvgPanel;


public class Korengui2 extends Composite {
	private static Korengui2UiBinder uiBinder = GWT.create(Korengui2UiBinder.class);

	interface Korengui2UiBinder extends UiBinder<Widget, Korengui2> {
	}

	@UiField
	NavLink topologyBtn;
	@UiField
	NavLink zoomBtn;
	@UiField
	NavLink pathflowBtn;

	@UiField
	TabPanel leftTabPanel1;
	@UiField
	SvgPanel rightTabPanel1;
	@UiField
	TablePanel rightTabPanel2;
	@UiField
	TabPanel leftTabPanel2;

	@UiField
	Button aboutBtn;
	@UiField
	AboutBox aboutBox;
	@UiField
	Alert alertStatus;
	@UiField
	NavLink reloadLink;


	public Korengui2() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@UiHandler("reloadLink")
	void onReladLinkClick(ClickEvent e){
		Window.Location.reload();
	}


	@UiHandler("aboutBtn")
	void onAboutBtnClick(ClickEvent e){
		aboutBox.show();
	}

	@UiHandler("topologyBtn")
	void onGetTopologyBtnClick(ClickEvent e) {
		Korengui.INSTANCE.getEventBus().deliverDownloadGlobalTopologyEvent(this);
		rightTabPanel1.showGlobalTopology();
	}

	@UiHandler("zoomBtn")
	void onZoomBtnClick(ClickEvent e) {
		Korengui.INSTANCE.getEventBus().deliverEventUserClickedZoomButton(this);


	}

	@UiHandler("pathflowBtn")
	void onPathFlowBtnClick(ClickEvent e) {
		Korengui.INSTANCE.getEventBus().deliverGetPathFlowEvent(this);
	}


	public SvgPanel getRightTabPanel1() {
		return rightTabPanel1;
	}

	public TablePanel getRightTabPanel2() {
		return rightTabPanel2;
	}

	public Alert getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(Alert alertStatus) {
		this.alertStatus = alertStatus;
	}




}
