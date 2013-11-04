package com.tranhoangdai.korengui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class InformationPanel extends TabLayoutPanel {

	public static InformationPanel INSTANCE = GWT.create(InformationPanel.class);
	
	public InformationPanel() {
		super(1.5, Unit.EM);
		setupNodeLinkInfoTab();
		setupPathFlowTab();
	}
	public InformationPanel(double barHeight, Unit barUnit) {
		super(barHeight, barUnit);
	}
	
	private void setupNodeLinkInfoTab(){
		NodeLinkInfoTab nodeLinkTab = new NodeLinkInfoTab(this);		
		add(nodeLinkTab,"Nodes/Links Information");
		
	}
	
	private void setupPathFlowTab(){
		PathFlowTab pathFlowTab = new PathFlowTab(this);		
		add(pathFlowTab,"Path Flow");
	}
	

}
