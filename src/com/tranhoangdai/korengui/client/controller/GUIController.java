package com.tranhoangdai.korengui.client.controller;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.ui.LoadingBox;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.SvgPanel;
import com.tranhoangdai.korengui.client.view.tab.svg.SvgPanelAbstractDrawTab;

public class GUIController {

	public static GUIController INSTANCE = GWT.create(GUIController.class);

	Label status;
	LoadingBox loadingBox = new LoadingBox();

	public void showLoading() {
		loadingBox.show();
	}
	public void closeLoading() {
		loadingBox.hide();
	}


	public void tellZoomInstruction() {
		status.setText("CLICK ON A NODE TO ZOOM IN");
	}

	public void tellDependentAction() {
		status.setText("PLEASE GET NETWORK TOPOLOGY FIRST");
	}

	public void tellPathFlowAction1(){
		status.setText("CLICK ON 2 NODES TO GET THE PATH-FLOW BETWEEN THEM, PLEASE SELECT THE FIRST ONE");
	}

	public void tellPathFlowAction2(){
		status.setText("PLEASE SELECT THE SECOND ONE");
	}

	public void clear() {
		status.setText("");
	}

	public void tellConnectedHostsToZoomIn() {
		Window.alert("This Node has no child Hosts to zoom in");
	}

	public Label getStatus() {
		return status;
	}

	public void setStatus(Label status) {
		this.status = status;
	}

	public void switchTabsInline(Integer tabNumber) {
		try {
			if(SvgPanel.INSTANCE.getWidgetCount() - 1 >= tabNumber){
				SvgPanel.INSTANCE.selectTab(tabNumber);
			}
			if(InfoPanel.INSTANCE.getWidgetCount() - 1 >= tabNumber){
				InfoPanel.INSTANCE.selectTab(tabNumber);
			}

		} catch (Exception e) {
			Log.debug("Other tab is not yet setup");
		}
	}

	public void closeTab(Widget tab){
		if (tab instanceof SvgPanelAbstractDrawTab) {
			SvgPanelAbstractDrawTab svgTab = (SvgPanelAbstractDrawTab) tab;
			SvgPanel.INSTANCE.closeTab(svgTab);

		}
	}

	public void tellSameId() {
		Window.alert("Please click on 2 different nodes");
	}

	public void showAboutDiablog() {

	}

	public void refreshWebApp() {
		Window.Location.reload();
	}
}
