package com.tranhoangdai.korengui.client.imp.node.zoom;

import java.util.HashMap;
import java.util.Map;

import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.imp.node.VisualNode;

public abstract class ZoomableNode extends VisualNode{
	
	Map<String,Node> childNodes = new HashMap<String,Node>();
	Map<Integer,NodeLink> childLinks = new HashMap<Integer,NodeLink>();
	
	public ZoomableNode(String ip, int x, int y) {
		super(ip, x, y);
	}	

	public Map<String, Node> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(Map<String, Node> childNodes) {
		this.childNodes = childNodes;
	}

	public Map<Integer, NodeLink> getChildLinks() {
		return childLinks;
	}

	public void setChildLinks(Map<Integer, NodeLink> childLinks) {
		this.childLinks = childLinks;
	}
	
	public void addChildNode(Node childNode){
		childNodes.put(childNode.getDpid(), childNode);
	}
	public void addChildLink(NodeLink childLink){
		childLinks.put(childLink.getId(), childLink);
	}
}
