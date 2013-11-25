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
import com.tranhoangdai.korengui.client.interf.GuiEventNotifier;
import com.tranhoangdai.korengui.client.interf.PathFlowNotifier;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;
import com.tranhoangdai.korengui.client.service.TopologyService;
import com.tranhoangdai.korengui.client.service.TopologyServiceAsync;

@SuppressWarnings("unused")
public class SvgPanel extends TabLayoutPanel implements GuiEventNotifier {

	public static SvgPanel INSTANCE = GWT.create(SvgPanel.class);

	private List<SvgPanelZoomTab> zoomTabs = new ArrayList<SvgPanelZoomTab>();

	public SvgPanel() {
		super(1.5, Unit.EM);
		setupEventHandlers();
	}

	private void setupEventHandlers() {

		// register to Utility event notifier
		Utility.INSTANCE.addGuiEventAble(this);

		// handler event when use changes tab, do nothing for now
		this.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {

			}
		});
	}

	SvgPanelGlobalTopologyTab globalTab = null;

	@Override
	public void eventGlobalTopology() {
		if (globalTab == null) {
			globalTab = new SvgPanelGlobalTopologyTab(this);
			this.add(globalTab, "Global");
			this.selectTab(globalTab);
		}
	}

	@Override
	public void eventCreateNewZoomNode(ZoomableNode zoomNode) {

		SvgPanelZoomTab tab = new SvgPanelZoomTab(this);
		zoomTabs.add(tab);
		String dpid = zoomNode.getDpid();
		this.add(tab, "Cluster \"" + dpid.substring(dpid.length() - 4, dpid.length()) + "\"");
		this.selectTab(tab);

	}

	SvgPanelPathFlowTab pfTab = null;

	@Override
	public void eventGetPathFlow(Node node) {
		if (pfTab == null) {
			pfTab = new SvgPanelPathFlowTab(this);
			add(pfTab, "Flow");
			selectTab(pfTab);
		}

	}

	public List<SvgPanelZoomTab> getZoomTabs() {
		return zoomTabs;
	}

}
