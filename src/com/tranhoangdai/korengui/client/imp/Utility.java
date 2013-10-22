package com.tranhoangdai.korengui.client.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.json.client.JSONObject;

public class Utility {
	
	public enum ActionState{
		NOTHING,ZOOMOUT,ZOOMIN
	}

	public static ActionState state = ActionState.NOTHING;
	public static List<Node> zoomStack = new ArrayList<Node>();
	public static Map<String, Node> activeNodes = new HashMap<String, Node>();
	public static Map<Integer, NodeLink> activeLinks = new HashMap<Integer, NodeLink>();

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

}
