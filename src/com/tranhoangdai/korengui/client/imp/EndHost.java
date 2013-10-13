package com.tranhoangdai.korengui.client.imp;

import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGTextElement;

import com.google.gwt.core.client.GWT;

public class EndHost extends VisualNode {

	public EndHost(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = GWT.getModuleBaseURL() + "images/endhost.svg";
		shape = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);
		textShape = new OMSVGTextElement(x - shape.getWidth().getBaseVal().getValue()/4, y, (short) 1, ip);
		
		setupEventHandler();
	}

}
