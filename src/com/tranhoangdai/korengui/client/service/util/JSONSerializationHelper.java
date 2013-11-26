package com.tranhoangdai.korengui.client.service.util;

import com.google.gwt.json.client.JSONObject;
import com.tranhoangdai.korengui.client.controller.Cluster;
import com.tranhoangdai.korengui.client.controller.EndHost;
import com.tranhoangdai.korengui.client.controller.Gateway;
import com.tranhoangdai.korengui.client.controller.Switch;
import com.tranhoangdai.korengui.client.controller.VisualNode;
import com.tranhoangdai.korengui.client.model.Node;

/**
 * 
 * @author dai-network-lab
 * 
 *         This class creates node and link from JSon data
 */
public class JSONSerializationHelper {

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

}
