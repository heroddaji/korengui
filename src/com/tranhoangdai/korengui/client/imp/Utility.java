package com.tranhoangdai.korengui.client.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.EndHost;
import com.tranhoangdai.korengui.client.imp.node.Gateway;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.imp.node.SvgUtility;
import com.tranhoangdai.korengui.client.imp.node.Switch;
import com.tranhoangdai.korengui.client.imp.node.VisualNode;
import com.tranhoangdai.korengui.client.imp.node.zoom.Cluster;
import com.tranhoangdai.korengui.client.imp.node.zoom.ZoomableNode;
import com.tranhoangdai.korengui.client.interf.GuiEventNotifier;
import com.tranhoangdai.korengui.client.interf.PathFlowNotifier;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;
import com.tranhoangdai.korengui.client.service.TopologyService;
import com.tranhoangdai.korengui.client.service.TopologyServiceAsync;

/**
 * This class handles interaction between nodes model and GUI
 */
public class Utility {

	public enum ActionState {
		NOTHING, GLOBAL, ZOOM, FLOW
	}

	public static Utility INSTANCE = GWT.create(Utility.class);
	public ActionState state = ActionState.NOTHING;
	public List<Node> zoomStack = new ArrayList<Node>();
	public Map<String, Node> globalNodes = new HashMap<String, Node>();
	public Map<Integer, NodeLink> globalLinks = new HashMap<Integer, NodeLink>();

	public List<TopologyNotifier> topologyNotifiers = new ArrayList<TopologyNotifier>();
	public List<PathFlowNotifier> pathFlowNotifiers = new ArrayList<PathFlowNotifier>();
	public List<GuiEventNotifier> guiEventNotifiers = new ArrayList<GuiEventNotifier>();
	public List<ZoomNotifier> zoomNotifiers = new ArrayList<ZoomNotifier>();

	

	public Node pathFlowNode1 = null;
	public Node pathFlowNode2 = null;

	public void setPathFlowConnection(Node node) {

		if (pathFlowNode1 != null && pathFlowNode2 != null) {
			return;
		}

		if (pathFlowNode1 == null) {
			pathFlowNode1 = node;
			notifyGuiEvent(ActionState.FLOW, node);
			notifyAddPathFlowStartNode();
		} else {
			pathFlowNode2 = node;			
		}

		if (pathFlowNode1.equals(pathFlowNode2)) {
			Window.alert("Error: please choose different nodes!");
			clearPathFlow();
		}
		
		//after 2 nodes are set, make the path		
		notifyAddPathFlowEndNode();

	}

	public void clearPathFlow() {
		state = ActionState.NOTHING;
		pathFlowNode1 = null;
		pathFlowNode2 = null;
		notifyClearFlow();
	}
	public void notifyGuiEvent(ActionState state, Object data) {
		if(state == ActionState.GLOBAL){
			for (GuiEventNotifier tn : guiEventNotifiers) {
				tn.eventGlobalTopology();
			}	
		}
		
		if(state == ActionState.FLOW){
			for (GuiEventNotifier tn : guiEventNotifiers) {
				tn.eventGetPathFlow((Node) data);
			}	
		}
		
		if(state == ActionState.ZOOM){
			for (GuiEventNotifier tn : guiEventNotifiers) {
				ZoomableNode node = (ZoomableNode) data;
				if(!SvgUtility.checkIfZoomNodeExist(node)){
					tn.eventCreateNewZoomNode(node);	
				}				
			}	
		}
	}

	public void notifyFinishDownloadGlobalTopology() {
		notifyGuiEvent(ActionState.GLOBAL, null);
		
		for (TopologyNotifier tn : topologyNotifiers) {
			tn.finishDownload(globalNodes, globalLinks);
		}
	}

	public void notifyAddPathFlowStartNode() {
		for (PathFlowNotifier pn : pathFlowNotifiers) {
			pn.addStartNode(pathFlowNode1);
		}
	}

	public void notifyAddPathFlowEndNode() {
		for (PathFlowNotifier pn : pathFlowNotifiers) {
			pn.addEndNode(pathFlowNode2);
		}
	}

	public void notifyClearFlow() {
		for (PathFlowNotifier pn : pathFlowNotifiers) {
			pn.emptyNodes();
		}
	}
	
	public void notifyZoomEvent(ZoomableNode zoomNode){
		for (ZoomNotifier zn : zoomNotifiers) {			
			zn.zoomIn(zoomNode);
		}
	}

	public void notifyFinishDownloadPathFlow(List<NodeLink> paths) {
		for (PathFlowNotifier pn : pathFlowNotifiers) {
			pn.pathIsSetup(paths);
		}

		pathFlowNode1 = null;
		pathFlowNode2 = null;
		state = ActionState.NOTHING;
	}

	public void downloadGlobalTopology() {
		downloadTopologySwitches();
	}

	public void downloadTopologySwitches() {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {

				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();

				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject jobj = array.get(i).isObject();
						createNode(jobj);
					}
					// finish download nodes info, now download links info
					downloadTopologyLinks();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load nodes");

			}
		};

		topo.getTopologySwitches(callback);
	}

	public void downloadTopologyLinks() {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {

				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();
				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject obj = array.get(i).isObject();
						String srcIp = obj.get("src-switch").isString().stringValue();
						int srcport = (int) obj.get("src-port").isNumber().doubleValue();
						String dstIp = obj.get("dst-switch").isString().stringValue();
						int dstport = (int) obj.get("dst-port").isNumber().doubleValue();
						NodeLink link = new NodeLink(srcIp, srcport, dstIp, dstport);

						link.findAndMatchNode(globalNodes);
						globalLinks.put(link.getId(), link);
					}

					fakeChildLink();

					// finish download links, notify finish download event
					notifyFinishDownloadGlobalTopology();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load links");

			}
		};

		topo.getTopologyLinks(callback);
	}

	public void downloafPathFlow(String nodeId1, String nodeId2) {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();
				List<NodeLink> paths = new ArrayList<NodeLink>();
				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject obj = array.get(i).isObject();
						String srcIp = obj.get("src-switch").isString().stringValue();
						int srcport = (int) obj.get("src-port").isNumber().doubleValue();
						String dstIp = obj.get("dst-switch").isString().stringValue();
						int dstport = (int) obj.get("dst-port").isNumber().doubleValue();
						NodeLink link = new NodeLink(srcIp, srcport, dstIp, dstport);
						paths.add(link);
					}

					notifyFinishDownloadPathFlow(paths);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load links");

			}
		};

		topo.getPathFlow(nodeId1, nodeId2, callback);
	}

	public void fakeChildLink() {
		for (Node node : globalNodes.values()) {
			if (node.getClass().equals(Cluster.class)) {
				((Cluster) node).fakeLinks();
			}
		}
	}

	public void createNode(JSONObject jobj) {
		Node activeNode = null;
		Node tempNode = null;

		String nodeId = jobj.get("dpid").isString().stringValue();
		tempNode = new VisualNode(nodeId, 0, 0);
		tempNode = setNodeProperties(tempNode, jobj);

		if (setChildNode(tempNode, jobj)) {
			// do nothing
		} else {
			activeNode = tempNode;
		}

		if (activeNode != null) {
			globalNodes.put(activeNode.getDpid(), activeNode);
		}
	}

	public Node setNodeProperties(Node tempNode, JSONObject jobj) {

		if (jobj.get("type") != null) {
			String type = jobj.get("type").isString().stringValue();

			if (type.equals("cluster")) {
				tempNode = new Cluster(tempNode.getDpid(), tempNode.getX(), tempNode.getY());
			} else if (type.equals("gateway")) {
				tempNode = new Gateway(tempNode.getDpid(), tempNode.getX(), tempNode.getY());
			} else if (type.equals("switch")) {
				tempNode = new Switch(tempNode.getDpid(), tempNode.getX(), tempNode.getY());
			} else if (type.equals("endhost")) {
				tempNode = new EndHost(tempNode.getDpid(), tempNode.getX(), tempNode.getY());
			}
		}

		return tempNode;
	}

	public boolean setChildNode(Node tempNode, JSONObject jobj) {

		boolean result = false;

		if (jobj.get("childOf") != null) {

			/*
			 * doesn't know why it wrap quote value into the string, so remove
			 * it
			 */
			String belongToId = jobj.get("childOf").isString().toString().replaceAll("\"", "");

			if (globalNodes.get(belongToId) != null) {
				Node parentNode = globalNodes.get(belongToId);
				((Cluster) parentNode).addChildNode(tempNode);
				result = true;
			}
		}
		return result;
	}

	public void notifyGuiWantToZoomToNode(ZoomableNode zoomNode) {		
		
		
		if (getState() == ActionState.ZOOM) {
			zoomStack.add(zoomNode);
			
			notifyGuiEvent(ActionState.ZOOM, zoomNode);
			notifyZoomEvent(zoomNode);			
			
			setState(ActionState.NOTHING);
		}
	}

	public ActionState getState() {
		return state;
	}

	public void setState(ActionState state) {
		this.state = state;
	}

	public List<Node> getZoomStack() {
		return zoomStack;
	}

	public void setZoomStack(List<Node> zoomStack) {
		this.zoomStack = zoomStack;
	}

	public Map<String, Node> getGlobalNodes() {
		return globalNodes;
	}

	public void setGlobalNodes(Map<String, Node> activeNodes) {
		globalNodes = activeNodes;
	}

	public Map<Integer, NodeLink> getGlobalLinks() {
		return globalLinks;
	}

	public void setGlobalinks(Map<Integer, NodeLink> activeLinks) {
		globalLinks = activeLinks;
	}

	public void addTopologyAble(TopologyNotifier topologyAble) {
		topologyNotifiers.add(topologyAble);
	}

	public void addPathFlowAble(PathFlowNotifier pathFlowAble) {
		pathFlowNotifiers.add(pathFlowAble);
	}
	
	public void addZoomAble(ZoomNotifier zoomAble) {
		zoomNotifiers.add(zoomAble);
	}

	public void addGuiEventAble(GuiEventNotifier guiEventAble) {
		guiEventNotifiers.add(guiEventAble);		
	}
	
}
