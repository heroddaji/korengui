package com.tranhoangdai.korengui.client.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.json.client.JSONObject;
import com.tranhoangdai.korengui.client.MapPanel;

public class Utility {
	
	public enum ActionState{
		NOTHING,ZOOMOUT,ZOOMIN
	}

	private static MapPanel mapPanel= null;
	

	private static ActionState state = ActionState.NOTHING;
	private static List<Node> zoomStack = new ArrayList<Node>();
	private static Map<String, Node> activeNodes = new HashMap<String, Node>();
	private static Map<Integer, NodeLink> activeLinks = new HashMap<Integer, NodeLink>();

	public static void createNode(JSONObject jobj) {
		Node activeNode = null;
		Node tempNode = null;

		String nodeId = jobj.get("dpid").isString().stringValue();
		tempNode = new VisualNode(nodeId, 0, 0);
		tempNode = setNodeProperties(tempNode, jobj);

		if (setChildNode(tempNode, jobj)) {
			//do nothing
		} else {
			activeNode = tempNode;
		}

		if (activeNode != null) {
			activeNodes.put(activeNode.getDpid(), activeNode);
		}
	}

	private static Node setNodeProperties(Node tempNode, JSONObject jobj) {

		if (jobj.get("type") != null) {
			String type = jobj.get("type").isString().stringValue();

			if (type.equals("cluster")) {
				tempNode = new Cluster(tempNode.getDpid(), tempNode.getX(), tempNode.getY());
				((Cluster)tempNode).setZoomAblePanel(mapPanel);
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

	private static boolean setChildNode(Node tempNode, JSONObject jobj) {

		boolean result = false;

		if (jobj.get("childOf") != null) {
			
			/* doesn't know why it wrap quote value into the string, so remove it */
			String belongToId = jobj.get("childOf").isString().toString().replaceAll("\"", ""); 

			if (activeNodes.get(belongToId) != null) {
				Node parentNode = activeNodes.get(belongToId);
				((Cluster) parentNode).addChildNode(tempNode);
				result = true;
			}
		}
		return result;
	}
	
	public static MapPanel getMapPanel() {
		return mapPanel;
	}

	public static void setMapPanel(MapPanel mapPanel) {
		Utility.mapPanel = mapPanel;
	}

	public static ActionState getState() {
		return state;
	}

	public static void setState(ActionState state) {
		Utility.state = state;
	}

	public static List<Node> getZoomStack() {
		return zoomStack;
	}

	public static void setZoomStack(List<Node> zoomStack) {
		Utility.zoomStack = zoomStack;
	}

	public static Map<String, Node> getActiveNodes() {
		return activeNodes;
	}

	public static void setActiveNodes(Map<String, Node> activeNodes) {
		Utility.activeNodes = activeNodes;
	}

	public static Map<Integer, NodeLink> getActiveLinks() {
		return activeLinks;
	}

	public static void setActiveLinks(Map<Integer, NodeLink> activeLinks) {
		Utility.activeLinks = activeLinks;
	}

}
