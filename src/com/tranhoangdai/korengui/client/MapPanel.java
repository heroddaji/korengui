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
public class MapPanel extends TabLayoutPanel implements Zoomable{

	public static MapPanel INSTANCE = GWT.create(MapPanel.class);

	TopologyAble nodeEvent;
	OMSVGSVGElement svg = null;
	Map<String, Panel> tabIndex = new HashMap<String, Panel>();

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
		tabIndex.put("Global", homeTab);
		this.add(homeTab, "Global");		

		initGlobalTab();
	}

	private void initGlobalTab() {
		for (Panel tab : tabIndex.values()) {
			loadGlobalTab(tab);
			// return immediately because only load first tab
			return;
		}
	}

	private void loadGlobalTab(Panel globalTab) {

		globalTab.setWidth(new Integer(Window.getClientWidth()) + "px");

		OMSVGDocument doc = OMSVGParser.currentDocument();
		svg = doc.createSVGSVGElement();

		svg.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientWidth());
		svg.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, this.getOffsetHeight());

		globalTab.getElement().appendChild(svg.getElement());
		getTopologySwitches();
	}

	private void layoutNodes() {

		float radius = this.getOffsetWidth() / 4;
		float center = 0;
		if (this.getOffsetHeight() < this.getOffsetWidth()) {
			center = this.getOffsetHeight() / 2;
		} else {
			center = this.getOffsetWidth() / 2;
		}
		float slice = (float) (2 * Math.PI / Utility.activeNodes.size());

		int counter = 1;
		try {

			for (Node node : Utility.activeNodes.values()) {
				int x = (int) (radius * Math.cos(counter * slice) + center);
				int y = (int) (radius * Math.sin(counter * slice) + center);
				((VisualNode) node).translateTo(x, y);
				++counter;
				svg.appendChild(node.getGroupShape());
			}

			getTopologyLinks();
		} catch (Exception e) {
			System.err.println(e);
			int a = 0;
		}
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

				layoutNodes();
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load nodes");

			}
		};

		topo.getTopologySwitches(callback);
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

							//TODO: move this link creation to Utility class
							JSONObject obj = array.get(i).isObject();
							String srcIp = obj.get("src-switch").isString().stringValue();
							int srcport = (int) obj.get("src-port").isNumber().doubleValue();
							String dstIp = obj.get("dst-switch").isString().stringValue();
							int dstport = (int) obj.get("dst-port").isNumber().doubleValue();
							NodeLink link = new NodeLink(srcIp, srcport, dstIp, dstport);
							link.findAndMatchNode(Utility.activeNodes);
							link.adjust();
							Utility.activeLinks.put(link.getId(), link);
							if (link.getShape() != null) {
								svg.getNode().insertFirst(link.getShape().getNode());
							}
							// after getting all nodes and linsk, notify the
							// event for callback implementation
							nodeEvent.gotTopology(Utility.activeNodes, Utility.activeLinks);

						} catch (Exception e) {
							System.out.println(e);
						}
					}
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load links");

			}
		};

		topo.getTopologyLinks(callback);
	}

	public OMSVGSVGElement getSvg() {
		return svg;
	}

	@Override
	public void zoomIn(Map<String, Node> nodes, Map<Integer, NodeLink> links) {		
		ScrollPanel zoomTab = new ScrollPanel();
		tabIndex.put("Zoom", zoomTab);
		this.add(zoomTab, (String) tabIndex.keySet().toArray()[tabIndex.size()-1]);		
	}

	@Override
	public void zoomOut() {
		
		
	}
}
