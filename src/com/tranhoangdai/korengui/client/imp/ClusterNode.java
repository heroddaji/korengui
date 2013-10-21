package com.tranhoangdai.korengui.client.imp;

import java.util.HashMap;
import java.util.Map;

import com.tranhoangdai.korengui.client.interf.Zoomable;

public class ClusterNode extends VisualNode implements Zoomable {
	
	Map<String,Node> childNodes = new HashMap<String,Node>();

	public ClusterNode(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = "images/cloud.svg";
		setupShape();
		setupTextShape();
		setupGroupShape();
		setupEventHandler();
		setHarole("Cluster");
	}
	
	public void addChildNode(Node childNode){
		childNodes.put(childNode.getDpid(), childNode);
	}

	@Override
	public boolean zoomIn() {
		Utility.zoomStack.add(this);
		
		
		
		return false;
	}

	@Override
	public boolean zoomOut() {
		Utility.zoomStack.remove(this);

		return false;
	}
	
}
