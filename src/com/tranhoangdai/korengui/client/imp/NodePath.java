package com.tranhoangdai.korengui.client.imp;

import org.vaadin.gwtgraphics.client.shape.Path;

public class NodePath extends Path {

	Node fromNode;
	Node toNode;
	static int x = 0;
	static int y = 0;
	int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

	public NodePath(int x, int y) {
		super(x, y);
	}

	//flag is use to seperate the 2 constructor
	public NodePath(int nodeId1, int nodeId2, boolean flag) {
		super(x, y);
		
		fromNode = NodeCanvas.getInstance().getNode(nodeId1);
		toNode = NodeCanvas.getInstance().getNode(nodeId2);
		setLoc();		
	}

	public void reSetUp() {		
		setLoc();		
	}

	private void setLoc() {
		for (int i = 0; i < getStepCount(); i++) {
			removeStep(i);
		}	
		x1 = fromNode.getX();
		y1 = fromNode.getY();
		x2 = toNode.getX();
		y2 = toNode.getY();
		moveTo(x1, y1);
		lineTo(x2, y2);
	}

}
