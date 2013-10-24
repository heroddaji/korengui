package com.tranhoangdai.korengui.client.imp.node.zoom;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.Utility.ActionState;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;

public class Cluster extends ZoomableNode  {
	
	public Cluster(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = "images/cloud.svg";
		setupShape();
		setupTextShape();
		setupGroupShape();
		setupEventHandler();
		setHarole("cluster");
	}
	
	@Override
	protected void handleMouseDownEvent(MouseDownEvent event) {
		Utility.INSTANCE.notifyGuiWantToZoomToNode(this);
		super.handleMouseDownEvent(event);
	}	
	
	
	

}
