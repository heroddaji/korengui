package com.tranhoangdai.korengui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.node.Node;
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

	InfoPanelGeneralInfoTab nodeLinkTab = null;

	@Override
	public void eventGlobalTopology() {
		if (nodeLinkTab == null) {
			nodeLinkTab = new InfoPanelGeneralInfoTab(this);
			add(nodeLinkTab, "Global");
		}
		else{
			selectTab(nodeLinkTab);
		}

	}

	InfoPanelPathFlowTab pathFlowTab = null;

	@Override
	public void eventGetPathFlow(Node node) {
		if (pathFlowTab == null) {
			pathFlowTab = new InfoPanelPathFlowTab(this);
			add(pathFlowTab, "Flow");
		}else{
			selectTab(pathFlowTab);
		}
	}

	@Override
	public void eventCreateNewZoomNode(ZoomableNode zoomNode) {
		InfoPanelZoomTab zoomTab = new InfoPanelZoomTab(this);
		String dpid = zoomNode.getDpid();
		add(zoomTab, "Cluster \"" + dpid.substring(dpid.length() - 4, dpid.length()) + "\"");

	}

}
