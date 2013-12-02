package com.tranhoangdai.korengui.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;

@SuppressWarnings("unused")
public class SvgPanel extends AbstractPanel  {

	public static SvgPanel INSTANCE = GWT.create(SvgPanel.class);
	
	List<SvgPanelZoomTab> zoomTabs = new ArrayList<SvgPanelZoomTab>();
	SvgPanelGlobalTopologyTab globalTab = null;
	SvgPanelPathFlowTab pfTab = null;	
	
	public SvgPanel() {
		super(1.5, Unit.EM);
		setupEventHandlers();
	}

	private void setupEventHandlers() {

		// handler event when use changes tab, do nothing for now
		this.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {

			}
		});
	}
	
	public void drawGlobalTopology(){
		if(globalTab != null){
			selectTab(globalTab);
		}
		else{
			globalTab = new SvgPanelGlobalTopologyTab(this);
			add(globalTab,"Network");
			globalTab.setSwitchModels(topologySwitches);
			globalTab.setLinkModels(topologyLinks);
			globalTab.draw();
		}
	}
	
	public void drawZoomTopology(Switch zoomSwitchModel, Map<String,Host> childHosts){
		for(SvgPanelZoomTab tab: zoomTabs){
			if(tab.hasZoomModel(zoomSwitchModel)){
				selectTab(tab);
				return;
			}
		}
		
		//if not exist, create new zoom tab
		SvgPanelZoomTab zoomTab = new SvgPanelZoomTab(this,zoomSwitchModel);
		zoomTab.setChildModels(childHosts);
		zoomTab.draw();
		
		//add to list of tabs
		zoomTabs.add(zoomTab);
		String name = zoomSwitchModel.getDpid();
		add(zoomTab, "Node " + name.substring(name.length() - 5, name.length()));
		selectTab(zoomTab);
	}


}
