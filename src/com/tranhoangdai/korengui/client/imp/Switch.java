package com.tranhoangdai.korengui.client.imp;

import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGTextElement;

import com.google.gwt.core.client.GWT;

public class Switch extends VisualNode {

	public Switch(String ip, int x, int y) {
		super(ip, x, y);
		imageHref = GWT.getModuleBaseURL() + "images/switch.svg";
		shape = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);
		textShape = new OMSVGTextElement(x - shape.getWidth().getBaseVal().getValue()/4, y, (short) 1, ip);
		
		setupEventHandler();
	}
}