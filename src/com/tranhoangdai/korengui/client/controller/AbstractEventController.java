package com.tranhoangdai.korengui.client.controller;

import java.util.HashMap;
import java.util.Map;

import com.tranhoangdai.korengui.client.model.Cluster;
import com.tranhoangdai.korengui.client.model.EndHost;
import com.tranhoangdai.korengui.client.model.Gateway;
import com.tranhoangdai.korengui.client.model.Node;
import com.tranhoangdai.korengui.client.model.NodeLink;
import com.tranhoangdai.korengui.client.model.Switch;

public class AbstractEventController {
	protected Map<String, Node> currentNodes = new HashMap<String, Node>();
	protected Map<Integer, NodeLink> currentLinks = new HashMap<Integer, NodeLink>();

	public <V, K> AbstractEventController(Map<K, V> nodes, Map<K, V> links) {
		cloneModelInformation(nodes, links);
	}

	public <K, V> void cloneModelInformation(Map<K, V> nodes, Map<K, V> links) {
		
		for (V _node : nodes.values()) {	
			Node node = (Node)_node; 
			if (node instanceof Switch) {				
				node = new Switch(node.getDpid(), node.getX(), node.getY());
			} else if (node instanceof Gateway) {
				node = new Gateway(node.getDpid(), node.getX(), node.getY());
			} else if (node instanceof EndHost) {
				node = new EndHost(node.getDpid(), node.getX(), node.getY());
			} else if (node instanceof Cluster) {
				node = new Cluster(node.getDpid(), node.getX(), node.getY());
				
			}
			currentNodes.put(node.getDpid(), node);
		}
	}
}
