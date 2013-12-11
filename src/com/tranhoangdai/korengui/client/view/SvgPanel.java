package com.tranhoangdai.korengui.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.tranhoangdai.korengui.client.EventBus;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.tab.svg.SvgPanelGlobalTopologyTab;
import com.tranhoangdai.korengui.client.view.tab.svg.SvgPanelPathFlowTab;
import com.tranhoangdai.korengui.client.view.tab.svg.SvgPanelZoomTab;

@SuppressWarnings("unused")
public class SvgPanel extends AbstractPanel  {

	public static SvgPanel INSTANCE = GWT.create(SvgPanel.class);
	
	List<SvgPanelZoomTab> zoomTabs = new ArrayList<SvgPanelZoomTab>();
	SvgPanelGlobalTopologyTab globalTab = null;
	SvgPanelPathFlowTab pfTab = null;	
	
	public SvgPanel() {
		super(1.5, Unit.EM);	
	}
	
	public void drawGlobalTopology(){
		Log.debug("Draw global network topology");
		if(globalTab != null){
			selectTab(globalTab, false);
		}
		else{
			globalTab = new SvgPanelGlobalTopologyTab(this);
			add(globalTab,"Network");
			globalTab.setSwitchModels(topologySwitches);
			globalTab.setLinkModels(topologyLinks);
			globalTab.draw();
		}
	}
	
	public void drawZoomTopology(Switch zoomSwitchModel, Map<String,Host> childHosts, Map<Integer, Link> linkModels){
		for(SvgPanelZoomTab tab: zoomTabs){
			if(tab.hasZoomModel(zoomSwitchModel)){
				selectTab(tab);
				return;
			}
		}
		
		//if not exist, create new zoom tab
		SvgPanelZoomTab zoomTab = new SvgPanelZoomTab(this,zoomSwitchModel);
		zoomTab.setChildModels(childHosts);
		zoomTab.setLinkModels(linkModels);
		zoomTab.draw();
		
		//add to list of tabs
		zoomTabs.add(zoomTab);
		String name = zoomSwitchModel.getId();
		add(zoomTab, "Node " + name.substring(name.length() - 5, name.length()));
		selectTab(zoomTab);
	}


}
