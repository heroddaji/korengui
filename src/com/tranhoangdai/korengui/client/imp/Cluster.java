package com.tranhoangdai.korengui.client.imp;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.tranhoangdai.korengui.client.imp.Utility.ActionState;
import com.tranhoangdai.korengui.client.interf.Zoomable;

public class Cluster extends VisualNode  {
	
	Map<String,Node> childNodes = new HashMap<String,Node>();
	Map<Integer,NodeLink> childLinks = new HashMap<Integer,NodeLink>();
	Zoomable zoomAblePanel;
	public Cluster(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = "images/cloud.svg";
		setupShape();
		setupTextShape();
		setupGroupShape();
		setupEventHandler();
		setHarole("cluster");
	}
	
	
	
	protected boolean zoom() {
		Utility.getZoomStack().add(this);
		zoomAblePanel.zoomIn(childNodes,childLinks);
		return false;
	}
	
	
	@Override
	protected void handleMouseDownEvent(MouseDownEvent event) {
		
		
		if(Utility.getState() == ActionState.ZOOMIN){
			zoom();
			Utility.setState(ActionState.NOTHING);
		}
		
		super.handleMouseDownEvent(event);
	}

	public Zoomable getZoomAblePanel() {
		return zoomAblePanel;
	}

	public void setZoomAblePanel(Zoomable zoomAblePanel) {
		this.zoomAblePanel = zoomAblePanel;
	}
	
	public void addChildNode(Node childNode){
		childNodes.put(childNode.getDpid(), childNode);
	}
	public void addChildLink(NodeLink childLink){
		childLinks.put(childLink.getId(), childLink);
	}
	

}
