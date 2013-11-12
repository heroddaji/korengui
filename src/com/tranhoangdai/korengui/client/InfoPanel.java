package com.tranhoangdai.korengui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.node.zoom.ZoomableNode;
import com.tranhoangdai.korengui.client.interf.GuiEventNotifier;

public class InfoPanel extends TabLayoutPanel implements GuiEventNotifier {

	public static InfoPanel INSTANCE = GWT.create(InfoPanel.class);
	
	public InfoPanel() {
		super(1.5, Unit.EM);
		Utility.INSTANCE.addGuiEventAble(this);
	
	}
	public InfoPanel(double barHeight, Unit barUnit) {
		super(barHeight, barUnit);
	}	
	
	@Override
	public void eventGlobalTopology() {
		InfoPanelNodeLinkInfoTab nodeLinkTab = new InfoPanelNodeLinkInfoTab(this);		
		add(nodeLinkTab,"Nodes/Links Information");
		
	}
	@Override
	public void eventZoomToNode(ZoomableNode zoomNode) {
		//create zoom tab	
		
	}
	@Override
	public void eventGetPathFlow() {
		InfoPanelPathFlowTab pathFlowTab = new InfoPanelPathFlowTab(this);		
		add(pathFlowTab,"Path Flow");
		
	}
	

}
