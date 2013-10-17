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
import com.tranhoangdai.korengui.client.imp.NodeLink;
import com.tranhoangdai.korengui.client.imp.Switch;
import com.tranhoangdai.korengui.client.imp.VisualNode;
import com.tranhoangdai.korengui.client.service.TopologyService;
import com.tranhoangdai.korengui.client.service.TopologyServiceAsync;

@SuppressWarnings("unused")
public class MapPanel extends AbsolutePanel {

	public static MapPanel INSTANCE = GWT.create(MapPanel.class);

	OMSVGSVGElement svg = null;		
	Map<String, Node>nodesmap = new HashMap<String,Node>();
	Map<Integer, NodeLink> links = new HashMap<Integer,NodeLink>();

	public MapPanel() {
		super();

		OMSVGDocument doc = OMSVGParser.currentDocument();
		svg = doc.createSVGSVGElement();

		svg.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, 600);
		svg.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, 600);
		
		RootPanel rootPanel = RootPanel.get();
		this.getElement().appendChild(svg.getElement());
	//	getTopologyLinks();
		getTopologySwitches();
		
	}
	
	private void layoutNodes(){
		
		float radius = 100;
		float center = 200;
		float slice = (float) (2 * Math.PI / nodesmap.size());
		
		int counter = 1;
		for(Node node: nodesmap.values()){
			float x = (float) (radius * Math.cos(counter * slice) + center);  
			float y = (float) (radius * Math.sin(counter * slice) + center);
			((VisualNode)node).setX(x);
			((VisualNode)node).setY(y);
			((VisualNode)node).adjustText(x, y); //must do this to reset the text location
			++counter;			
			svg.appendChild(node.getShape());			
			svg.appendChild(node.getTextShape());
		}
		
		getTopologyLinks();
	}

	private void getTopologySwitches() {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {

				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();
				
				if (array != null) {
					for(int i = 0 ; i < array.size(); i++){
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
					for(int i = 0 ; i < array.size(); i++){
						JSONObject obj = array.get(i).isObject();
						String srcIp = obj.get("src-switch").isString().stringValue();
						int srcport = (int) obj.get("src-port").isNumber().doubleValue();
						String dstIp = obj.get("dst-switch").isString().stringValue();
						int dstport = (int) obj.get("dst-port").isNumber().doubleValue();
						NodeLink link = new NodeLink(srcIp, srcport, dstIp, dstport);
						link.findAndMatchNode(nodesmap);
						link.adjust();
						links.put(link.getId(), link);
						
						svg.getNode().insertFirst(link.getShape().getNode());
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
