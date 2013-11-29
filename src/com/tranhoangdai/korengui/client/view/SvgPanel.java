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

@SuppressWarnings("unused")
public class SvgPanel extends AbstractPanel  {

	public static SvgPanel INSTANCE = GWT.create(SvgPanel.class);
	private List<SvgPanelZoomTab> zoomTabs = new ArrayList<SvgPanelZoomTab>();
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
			globalTab.draw();
		}
	}
	
	

//	@Override
//	public void eventCreateNewZoomNode(ZoomableNode zoomNode) {
//
//		SvgPanelZoomTab tab = new SvgPanelZoomTab(this);
//		zoomTabs.add(tab);
//		String dpid = zoomNode.getDpid();
//		this.add(tab, "Cluster \"" + dpid.substring(dpid.length() - 4, dpid.length()) + "\"");
//		this.selectTab(tab);
//
//	}
//
//	
//	@Override
//	public void eventGetPathFlow(Node node) {
//		if (pfTab == null) {
//			pfTab = new SvgPanelPathFlowTab(this);
//			add(pfTab, "Flow");
//			selectTab(pfTab);
//		}
//
//	}

	public List<SvgPanelZoomTab> getZoomTabs() {
		return zoomTabs;
	}

}
