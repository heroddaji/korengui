package com.tranhoangdai.korengui.client.controller;

import javax.swing.text.TabExpander;

import com.allen_sauer.gwt.log.client.Log;
import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.TabPane;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.Korengui;
import com.tranhoangdai.korengui.client.Korengui2;
import com.tranhoangdai.korengui.client.ui.LoadingBox;
import com.tranhoangdai.korengui.client.ui.SvgPanel;
import com.tranhoangdai.korengui.client.ui.TablePanel;

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

	public void switchTabsInline(Object dstTab) {
		TabPane tab = (TabPane)dstTab;

		SvgPanel svgPanel = Korengui.INSTANCE.getKorengui2().getRightTabPanel1();
		TablePanel tablePanel = Korengui.INSTANCE.getKorengui2().getRightTabPanel2();

		try {
			int index = -1;
			if(svgPanel.hasTab(tab) > -1 ){
				index = svgPanel.hasTab(tab);
			}
			if(tablePanel.hasTab(tab) > -1 ){
				index = tablePanel.hasTab(tab);
			}
			if(index > -1){
				svgPanel.getPanel().selectTab(index);
				tablePanel.getPanel().selectTab(index);
			}

		} catch (Exception e) {
			Log.debug("Other tab is not yet setup");
		}
	}

	public void closeTab(Widget tab){

	}

	public void tellSameId() {
		Window.alert("Please click on 2 different nodes");
	}



	public void refreshWebApp() {

	}
}
