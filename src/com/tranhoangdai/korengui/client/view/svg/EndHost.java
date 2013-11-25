package com.tranhoangdai.korengui.client.view.svg;


public class EndHost extends VisualNode {

	public EndHost() {
	}

	public EndHost(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = "images/endhost.svg";
		setupShape();
		setupTextShape();
		setupGroupShape();
		setupEventHandler();
		setHarole("endhost");
	
	}

}
