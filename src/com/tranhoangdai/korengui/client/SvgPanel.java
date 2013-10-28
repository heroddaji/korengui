package com.tranhoangdai.korengui.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.imp.node.VisualNode;
import com.tranhoangdai.korengui.client.imp.node.zoom.ZoomableNode;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;
import com.tranhoangdai.korengui.client.service.TopologyService;
import com.tranhoangdai.korengui.client.service.TopologyServiceAsync;

@SuppressWarnings("unused")
public class SvgPanel extends TabLayoutPanel implements TopologyNotifier {

	public static SvgPanel INSTANCE = GWT.create(SvgPanel.class);
	private SvgPanelTab tempTab = null;

	public SvgPanel() {
		super(1.5, Unit.EM);
		setupEventHandlers();

	}

	private void setupEventHandlers() {

		// register to Utility event notifier
		Utility.INSTANCE.addTopologyAble(this);

		// handler event when use changes tab, do nothing for now
		this.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {

			}
		});
	}
	

	public void setupZoomTab(ZoomableNode zoomNode) {
		SvgPanelTab tab = new SvgPanelTab();	
		this.add(tab, "Zoom Node:" + zoomNode.getDpid());
		tab.setHeight(String.valueOf(Window.getClientHeight()) + "px");
		tab.setWidth("100%");
		this.selectTab(tab);
		tab.setNodesAndLinks(zoomNode.getChildNodes(), zoomNode.getChildLinks());		
		tab.draw();
		
	}

	public void setupGlobalTopology() {
		SvgPanelTab tab = new SvgPanelTab();
		this.add(tab, "Global Topology");
		tempTab = tab;
		// call global services on the server
		Utility.INSTANCE.downloadGlobalTopology();		
		this.selectTab(tab);
	}

	public void finishDownload(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		this.tempTab.setNodesAndLinks(nodes, links);
		this.tempTab.draw();
	}
}
