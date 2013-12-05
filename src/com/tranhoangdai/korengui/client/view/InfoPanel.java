package com.tranhoangdai.korengui.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.tab.info.InfoPanelGlobalTopologyTab;
import com.tranhoangdai.korengui.client.view.tab.info.InfoPanelPathFlowTab;
import com.tranhoangdai.korengui.client.view.tab.info.InfoPanelZoomTab;

public class InfoPanel extends AbstractPanel {

	public static InfoPanel INSTANCE = GWT.create(InfoPanel.class);
	InfoPanelGlobalTopologyTab globalTab = null;
	InfoPanelPathFlowTab pathFlowTab = null;
	List<InfoPanelZoomTab> zoomTabs = new ArrayList<InfoPanelZoomTab>();
	
	public InfoPanel() {
		super(1.5, Unit.EM);
	}

	public void showGlobalTopology() {
		if (globalTab == null) {
			globalTab = new InfoPanelGlobalTopologyTab(this);
			add(globalTab, "Network");
			globalTab.showInfo();
		} else {
			selectTab(globalTab);
		}
	}

	public void showZoomTopology(Switch zoomSwitchModel, Map<String, Host> childHosts, Map<Integer, Link> linkModels) {
		
		for(InfoPanelZoomTab tab: zoomTabs){
			if(tab.hasZoomModel(zoomSwitchModel)){
				selectTab(tab);
				return;
			}
		}
		
		//not exist zoom tab, create new one
		InfoPanelZoomTab zoomTab = new InfoPanelZoomTab(this, zoomSwitchModel);
		zoomTab.setChildModels(childHosts);
		zoomTab.setLinkModels(linkModels);
		zoomTab.showInfo();
		
		String name = zoomSwitchModel.getId();
		add(zoomTab, "Node " + name.substring(name.length() - 5, name.length()));
		selectTab(zoomTab);
	}

}
