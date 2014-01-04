package com.tranhoangdai.korengui.client.controller;

import com.github.gwtbootstrap.client.ui.Alert;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.Korengui;
import com.tranhoangdai.korengui.client.Korengui2;
import com.tranhoangdai.korengui.client.ui.LoadingBox;
import com.tranhoangdai.korengui.client.view.SvgPanel;
import com.tranhoangdai.korengui.client.view.tab.svg.SvgPanelAbstractDrawTab;

public class GUIController {

	Korengui2 koren2 = null;
	Alert alertStatus = null;
	LoadingBox loadingBox = new LoadingBox();

	public GUIController(){
		alertStatus = Korengui.INSTANCE.getKorengui2().getAlertStatus();
	}


	public void showLoading() {
		loadingBox.show();
	}
	public void closeLoading() {
		loadingBox.hide();
	}


	public void tellZoomInstruction() {

		alertStatus.clear();
		alertStatus.setText("Select a Node to zoom into it");
	}

	public void tellDependentAction() {
		alertStatus.clear();
		alertStatus.setText("Get network topology first");
	}

	public void tellPathFlowAction1(){
		alertStatus.clear();
		alertStatus.setText("Select 2 different nodes to get the path-flow connection");
	}

	public void tellPathFlowAction2(){
		alertStatus.clear();
		alertStatus.setText("Now select the second Node");
	}

	public void clear() {
		alertStatus.clear();
		alertStatus.setText("");
	}

	public void tellEmptyNode() {
		alertStatus.clear();
		alertStatus.setText("This node is empty");
	}

	public void switchTabsInline(Integer tabNumber) {
//		try {
//			if(SvgPanel.INSTANCE.getWidgetCount() - 1 >= tabNumber){
//				SvgPanel.INSTANCE.selectTab(tabNumber);
//			}
//			if(InfoPanel.INSTANCE.getWidgetCount() - 1 >= tabNumber){
//				InfoPanel.INSTANCE.selectTab(tabNumber);
//			}
//
//		} catch (Exception e) {
//			Log.debug("Other tab is not yet setup");
//		}
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



	public void refreshWebApp() {

	}
}
