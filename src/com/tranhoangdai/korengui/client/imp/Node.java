package com.tranhoangdai.korengui.client.imp;

import java.util.HashMap;

import org.vectomatic.dom.svg.OMSVGElement;

import com.google.gwt.event.dom.client.MouseEvent;

public abstract class Node extends SvgElement {

	int WIDTH = 40;
	int HEIGHT = 40;
	String ipAddress;
	HashMap<Integer, NodePath> paths = new HashMap<Integer, NodePath>();
	int x;
	int y;
	final float scaleFactor = 1.5f;

	public Node(String ipAddress, int x, int y) {
		this.x = x;
		this.y = y;
		this.ipAddress = ipAddress;
	}

	protected void addPath(NodePath path) {
		paths.put(path.getId(), path);
	}

	public abstract OMSVGElement getTextShape();

	public abstract void move(MouseEvent<?> event);

	public void calculateForces() {

	}

}
