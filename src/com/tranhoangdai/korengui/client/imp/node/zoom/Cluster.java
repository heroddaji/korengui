package com.tranhoangdai.korengui.client.imp.node.zoom;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.Utility.ActionState;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.imp.node.Switch;

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

			NodeLink link = new NodeLink(srcNode.getDpid(), 1, dstNode.getDpid(), 1);
			link.findAndMatchNode(childNodes);
			addChildLink(link);			
		}		
		
	}

	@Override
	protected void handleMouseDownEvent(MouseDownEvent event) {
		Utility.INSTANCE.notifyGuiWantToZoomToNode(this);
		if (Utility.INSTANCE.getState() == ActionState.ZOOMIN) {
			Utility.INSTANCE.notifyGuiWantToZoomToNode(this);
		}
		super.handleMouseDownEvent(event);
	}

}