package com.tranhoangdai.korengui.client.imp.node;

import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.OMText;

import com.google.gwt.core.client.GWT;

public class Gateway extends VisualNode {

	public Gateway() {
	}

	public Gateway(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = "images/router.svg";	
		setupShape();
		setupTextShape();
		setupGroupShape();
		setupEventHandler();
		setHarole("gateway"); 
	}
	

}
