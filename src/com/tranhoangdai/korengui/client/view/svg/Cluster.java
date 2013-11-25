package com.tranhoangdai.korengui.client.view.svg;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.controller.Utility.ActionState;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Node;

public class Cluster extends ZoomableNode {

	public Cluster(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = "images/cloud.svg";
		setupShape();
		setupTextShape();
		setupGroupShape();
		setupEventHandler();
		setHarole("cluster");

		setupClusterSwitch();
		fakeLinks();
	}

	protected void setupClusterSwitch() {
		Switch clusterSwitch = new Switch(this.getDpid(), this.getX(), this.getY());
		addChildNode(clusterSwitch);

	}

	public void fakeLinks() {

		Node srcNode = (Node) childNodes.get(this.getDpid());
		for (int i = 0; i < childNodes.values().size(); i++) {		
			Node dstNode = (Node) childNodes.values().toArray()[i];
			if(dstNode.getDpid().equals(srcNode.getDpid())){
				continue;
			}			

			Link link = new Link(srcNode.getDpid(), 1, dstNode.getDpid(), 1);
			link.findAndMatchNode(childNodes);
			addChildLink(link);			
		}		
		
	}

	@Override
	protected void handleMouseDownEvent(MouseDownEvent event) {		
		if (Utility.INSTANCE.getState() == ActionState.ZOOM) {
			Utility.INSTANCE.notifyGuiWantToZoomToNode(this);
		}
		super.handleMouseDownEvent(event);
	}

}
