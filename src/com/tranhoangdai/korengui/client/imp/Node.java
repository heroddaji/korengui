package com.tranhoangdai.korengui.client.imp;

import com.google.gwt.dev.util.collect.HashSet;


public abstract class Node  {
	
	int id;
	String name;
	int parentId;
	HashSet<Integer> connectedNodes = new HashSet<Integer>();
	
	public Node(int id, String name){
		this.id = id;
		this.name = name;
	}
	
}
