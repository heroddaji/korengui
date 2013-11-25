package com.tranhoangdai.korengui.client.view.svg;

import java.util.HashMap;
import java.util.Map;

import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Node;

public abstract class ZoomableNode extends VisualNode{
	
	Map<String,Node> childNodes = new HashMap<String,Node>();
	Map<Integer,Link> childLinks = new HashMap<Integer,Link>();
	
	public ZoomableNode(String ip, int x, int y) {
		super(ip, x, y);
	}	

	public Map<String, Node> getChildNodes() {
		return childNodes;
	}	

	public Map<Integer, Link> getChildLinks() {
		return childLinks;
	}
	
	
	public void addChildNode(Node childNode){
		
		childNodes.put(childNode.getDpid(), childNode);
	}
	public void addChildLink(Link childLink){
		childLinks.put(childLink.getId(), childLink);
	}
}
