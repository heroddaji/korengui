package com.tranhoangdai.korengui.client.imp;

import java.util.HashMap;


public abstract class Node  extends SvgElement{	
	
	int WIDTH = 50;
	int HEIGHT = 50;
	String ipAddress;	
	HashMap<Integer, NodePath>paths = new HashMap<Integer, NodePath>();
	int x;
	int y;
	
	public Node(String ipAddress, int x, int y){
		this.x = x;
		this.y = y;
		this.ipAddress = ipAddress;
	}
	
	protected void addPath(NodePath path){
		paths.put(path.getId(),path);
	}
	
	
	
}
