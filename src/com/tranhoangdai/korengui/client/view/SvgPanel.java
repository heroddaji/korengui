package com.tranhoangdai.korengui.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.TabLayoutPanel;

@SuppressWarnings("unused")
public class SvgPanel extends TabLayoutPanel  {

	public static SvgPanel INSTANCE = GWT.create(SvgPanel.class);

	private List<SvgPanelZoomTab> zoomTabs = new ArrayList<SvgPanelZoomTab>();

	public SvgPanel() {
		super(1.5, Unit.EM);
		setupEventHandlers();
	}

	private void setupEventHandlers() {

		// register to Utility event notifier
	//	Utility.INSTANCE.addGuiEventAble(this);

		// handler event when use changes tab, do nothing for now
		this.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {

			}
		});
	}

	SvgPanelGlobalTopologyTab globalTab = null;

	

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
//	SvgPanelPathFlowTab pfTab = null;
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
