package com.tranhoangdai.korengui.client.imp;

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
		imageHref = GWT.getModuleBaseURL() + "images/router.svg";
		shape = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);
		setHarole("Gateway");
		setupEventHandler();
		
	}

}
