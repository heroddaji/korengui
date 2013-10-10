package com.tranhoangdai.korengui.client.imp;

import java.util.ArrayList;

public class Utility {
	private static int uniqueId = -1;
	private static ArrayList<Node> rootNodes = new ArrayList<Node>();
	private static ArrayList<Node> rootPaths = new ArrayList<Node>();
	
	public static int nextId(){
		return ++uniqueId;
	}
	public static ArrayList<Node> getRootNodes(){
		return rootNodes;
	}
	public static void setRootNodes(ArrayList<Node> ref){
		rootNodes = ref;
	}
}
