package com.tranhoangdai.korengui.client.model;

import java.util.HashMap;
import java.util.Map;

public abstract class ZoomableNode extends VisualNode{
	
	Map<String,Node> childNodes = new HashMap<String,Node>();
	Map<Integer,NodeLink> childLinks = new HashMap<Integer,NodeLink>();
	
	public ZoomableNode(String ip, int x, int y) {
		super(ip, x, y);
	}	

	public Map<String, Node> getChildNodes() {
		return childNodes;
	}	

	public Map<Integer, NodeLink> getChildLinks() {
		return childLinks;
	}
	
	
	public void addChildNode(Node childNode){
		
		childNodes.put(childNode.getDpid(), childNode);
	}
	public void addChildLink(NodeLink childLink){
		childLinks.put(childLink.getId(), childLink);
	}
}
