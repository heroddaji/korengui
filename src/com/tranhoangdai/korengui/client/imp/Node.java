package com.tranhoangdai.korengui.client.imp;

import java.util.HashMap;

import org.vectomatic.dom.svg.OMSVGElement;

public abstract class Node extends SvgElement {

	int WIDTH = 40;
	int HEIGHT = 40;
	String ipAddress;
	HashMap<Integer, NodePath> paths = new HashMap<Integer, NodePath>();
	int x;
	int y;

	public Node(String ipAddress, int x, int y) {
		this.x = x;
		this.y = y;
		this.ipAddress = ipAddress;	
	}

	protected void addPath(NodePath path) {
		paths.put(path.getId(), path);
	}	
	
	public void move(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public abstract OMSVGElement getTextShape();
	
	public void calculateForces(){
		
	}
		
}
