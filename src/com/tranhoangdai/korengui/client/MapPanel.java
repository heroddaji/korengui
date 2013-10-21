package com.tranhoangdai.korengui.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.tranhoangdai.korengui.client.imp.Node;
import com.tranhoangdai.korengui.client.imp.NodeEvent;
import com.tranhoangdai.korengui.client.imp.NodeLink;
import com.tranhoangdai.korengui.client.imp.Switch;
import com.tranhoangdai.korengui.client.imp.VisualNode;
import com.tranhoangdai.korengui.client.service.TopologyService;
import com.tranhoangdai.korengui.client.service.TopologyServiceAsync;

@SuppressWarnings("unused")
public class MapPanel extends AbsolutePanel {

	public static MapPanel INSTANCE = GWT.create(MapPanel.class);

	NodeEvent nodeEvent;

	OMSVGSVGElement svg = null;
	Map<String, Node> nodesmap = new HashMap<String, Node>();
	Map<Integer, NodeLink> links = new HashMap<Integer, NodeLink>();

	public MapPanel() {
		super();
	}

	public void setNodeEvent(NodeEvent e) {
		this.nodeEvent = e;
	}

	public void loadNodes() {
		// avoid multiple reloads from button
		if (svg != null) {
			return;
		}
		OMSVGDocument doc = OMSVGParser.currentDocument();
		svg = doc.createSVGSVGElement();

		svg.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, this.getOffsetWidth());
		svg.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, this.getOffsetHeight());

		RootPanel rootPanel = RootPanel.get();
		this.getElement().appendChild(svg.getElement());
		// getTopologyLinks();
		getTopologySwitches();
	}

	private void layoutNodes() {

		float radius = this.getOffsetWidth() / 4;
		float center = this.getOffsetWidth() / 2;
		float slice = (float) (2 * Math.PI / nodesmap.size());

		int counter = 1;
		try {

			for (Node node : nodesmap.values()) {
				float x = (float) (radius * Math.cos(counter * slice) + center);
				float y = (float) (radius * Math.sin(counter * slice) + center);				
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
						JSONObject obj = array.get(i).isObject();
						Switch theSwitch = new Switch(obj.get("dpid").isString().stringValue(), 0, 0);
						nodesmap.put(theSwitch.getDpid(), theSwitch);
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

							JSONObject obj = array.get(i).isObject();
							String srcIp = obj.get("src-switch").isString().stringValue();
							int srcport = (int) obj.get("src-port").isNumber().doubleValue();
							String dstIp = obj.get("dst-switch").isString().stringValue();
							int dstport = (int) obj.get("dst-port").isNumber().doubleValue();
							NodeLink link = new NodeLink(srcIp, srcport, dstIp, dstport);
							link.findAndMatchNode(nodesmap);
							link.adjust();
							links.put(link.getId(), link);
							if (link.getShape() != null) {
								svg.getNode().insertFirst(link.getShape().getNode());
							}

							// after getting all nodes and linsk, notify the
							// event for callback implementation
							nodeEvent.gotTopology(nodesmap, links);

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
}
