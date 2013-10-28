package com.tranhoangdai.korengui.client.imp.node.zoom;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.Utility.ActionState;
import com.tranhoangdai.korengui.client.imp.node.Switch;

public class Cluster extends ZoomableNode  {

	
	public Cluster(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = "images/cloud.svg";
		setupShape();
		setupTextShape();
		setupGroupShape();
		setupEventHandler();
		setHarole("cluster");
		
		setupClusterSwitch();
	}
	
	protected void setupClusterSwitch(){
		Switch clusterSwitch = new Switch(this.getDpid(), this.getX(), this.getY());				
		addChildNode(clusterSwitch);
		
	}
	
	@Override
	protected void handleMouseDownEvent(MouseDownEvent event) {
		Utility.INSTANCE.notifyGuiWantToZoomToNode(this);
		if(Utility.INSTANCE.getState() == ActionState.ZOOMIN){
			Utility.INSTANCE.notifyGuiWantToZoomToNode(this);
		}
		super.handleMouseDownEvent(event);
	}	
	
	
	

}
