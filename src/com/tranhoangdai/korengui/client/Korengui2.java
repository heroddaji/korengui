package com.tranhoangdai.korengui.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.ui.AboutBox;
import com.tranhoangdai.korengui.client.ui.DrawingPanel;

public class Korengui2 extends Composite {

	private static Korengui2UiBinder uiBinder = GWT.create(Korengui2UiBinder.class);

	interface Korengui2UiBinder extends UiBinder<Widget, Korengui2> {
	}

	@UiField
	NavLink topologyBtn;

	@UiField
	TabPanel rightTabPanel1;
	@UiField
	TabPanel leftTabPanel1;
	@UiField
	TabPanel rightTabPanel2;
	@UiField
	TabPanel leftTabPanel2;
	@UiField
	DrawingPanel globalDrawingPanel;
	@UiField
	Button aboutBtn;
	@UiField
	AboutBox aboutBox;


	public Korengui2() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("aboutBtn")
	void onAboutBtnClick(ClickEvent e){
		aboutBox.show();
	}

	@UiHandler("topologyBtn")
	void onGetTopologyBtnClick(ClickEvent e) {
		EventBus.INSTANCE.deliverDownloadGlobalTopologyEvent(this);
	}

	public TabPanel getRightTabPanel1() {
		return rightTabPanel1;
	}

	public void setRightTabPanel1(TabPanel rightTabPanel1) {
		this.rightTabPanel1 = rightTabPanel1;
	}

	public TabPanel getLeftTabPanel1() {
		return leftTabPanel1;
	}

	public void setLeftTabPanel1(TabPanel leftTabPanel1) {
		this.leftTabPanel1 = leftTabPanel1;
	}

	public TabPanel getRightTabPanel2() {
		return rightTabPanel2;
	}

	public void setRightTabPanel2(TabPanel rightTabPanel2) {
		this.rightTabPanel2 = rightTabPanel2;
	}

	public TabPanel getLeftTabPanel2() {
		return leftTabPanel2;
	}

	public void setLeftTabPanel2(TabPanel leftTabPanel2) {
		this.leftTabPanel2 = leftTabPanel2;
	}

	public DrawingPanel getGlobalDrawingPanel() {
		return globalDrawingPanel;
	}

	public void setGlobalDrawingPanel(DrawingPanel globalDrawingPanel) {
		this.globalDrawingPanel = globalDrawingPanel;
	}


}
