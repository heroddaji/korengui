package com.tranhoangdai.korengui.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.imp.Cluster;
import com.tranhoangdai.korengui.client.imp.Gateway;
import com.tranhoangdai.korengui.client.imp.Node;
import com.tranhoangdai.korengui.client.imp.NodeLink;
import com.tranhoangdai.korengui.client.imp.Switch;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.VisualNode;
import com.tranhoangdai.korengui.client.interf.TopologyAble;
import com.tranhoangdai.korengui.client.interf.Zoomable;
import com.tranhoangdai.korengui.client.service.TopologyService;
import com.tranhoangdai.korengui.client.service.TopologyServiceAsync;

@SuppressWarnings("unused")
public class MapPanel extends TabLayoutPanel implements Zoomable {

	public static MapPanel INSTANCE = GWT.create(MapPanel.class);

	TopologyAble nodeEvent;
	OMSVGSVGElement svg = null;

	public void setNodeEvent(TopologyAble e) {
		this.nodeEvent = e;
	}

	public MapPanel(double barHeight, Unit barUnit) {
		super(barHeight, barUnit);

	}

	public MapPanel() {
		super(1.5, Unit.EM);

	}

	public void init() {
		ScrollPanel homeTab = new ScrollPanel();
		this.add(homeTab, "Global");
		Utility.setMapPanel(this);
		initGlobalTab();
	}

	private void initGlobalTab() {

		Panel tab = (Panel) this.getWidget(0);
		prepareSvgForTab(tab);

		// load global topology
		getTopologySwitches();

	}

	private void prepareSvgForTab(Panel tab) {

		tab.setWidth(new Integer(Window.getClientWidth()) + "px");
		OMSVGDocument doc = OMSVGParser.currentDocument();
		svg = doc.createSVGSVGElement();
		svg.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientWidth());
		svg.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, this.getOffsetHeight());
		tab.getElement().appendChild(svg.getElement());
	}

	private void getTopologySwitches() {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {

				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();

				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject jobj = array.get(i).isObject();
						Utility.createNode(jobj);
					}
				}

				layoutNodes(Utility.getActiveNodes());
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load nodes");

			}
		};

		topo.getTopologySwitches(callback);
	}

	private void layoutNodes(Map<String, Node> nodes) {

		float radius = this.getOffsetWidth() / 4;
		float center = 0;
		if (this.getOffsetHeight() < this.getOffsetWidth()) {
			center = this.getOffsetHeight() / 2;
		} else {
			center = this.getOffsetWidth() / 2;
		}
		float slice = (float) (2 * Math.PI / nodes.size());

		int counter = 1;
		try {

			for (Node node : nodes.values()) {
				int x = (int) (radius * Math.cos(counter * slice) + center);
				int y = (int) (radius * Math.sin(counter * slice) + center);
				((VisualNode) node).translateTo(x, y);
				++counter;
				svg.appendChild(node.getGroupShape());
			}

			getTopologyLinks();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void getTopologyLinks() {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {

				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();
				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						try {

							// TODO: move this link creation to Utility class
							JSONObject obj = array.get(i).isObject();
							String srcIp = obj.get("src-switch").isString().stringValue();
							int srcport = (int) obj.get("src-port").isNumber().doubleValue();
							String dstIp = obj.get("dst-switch").isString().stringValue();
							int dstport = (int) obj.get("dst-port").isNumber().doubleValue();
							NodeLink link = new NodeLink(srcIp, srcport, dstIp, dstport);
							link.findAndMatchNode(Utility.getActiveNodes());
							link.adjust();
							Utility.getActiveLinks().put(link.getId(), link);

						} catch (Exception e) {
							System.out.println(e);
						}
					}
					layoutLinks(Utility.getActiveLinks());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load links");

			}
		};

		topo.getTopologyLinks(callback);
	}

	private void layoutLinks(Map<Integer, NodeLink> links) {
		for (NodeLink link : links.values()) {
			if (link.getShape() != null) {
				svg.getNode().insertFirst(link.getShape().getNode());
			}
		}

		notifyNodeEventInterface();
	}

	private void notifyNodeEventInterface() {
		// after getting all nodes and linsk, notify the
		// event for callback implementation
		nodeEvent.gotTopology(Utility.getActiveNodes(), Utility.getActiveLinks());
	}

	public OMSVGSVGElement getSvg() {
		return svg;
	}

	@Override
	public void zoomIn(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		ScrollPanel zoomTab = new ScrollPanel();
		this.add(zoomTab, "Zoom");
		layoutNodes(nodes);
		layoutLinks(links);
	}

	@Override
	public void zoomOut() {

	}
}
