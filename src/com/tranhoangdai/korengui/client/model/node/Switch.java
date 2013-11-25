package com.tranhoangdai.korengui.client.model.node;


public class Switch extends VisualNode {

	public Switch() {
	}

	public Switch(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = "images/switch.svg";		
		setupShape();
		setupTextShape();
		setupGroupShape();
		setupEventHandler();
		setHarole("switch");		
		
	}
}
