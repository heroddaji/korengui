package com.tranhoangdai.korengui.client.imp;

import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGTextElement;

import com.google.gwt.core.client.GWT;

public class Switch extends VisualNode {

	public Switch() {
	}

	public Switch(String ip, int x, int y) {
		super(ip, x, y);
		//imageHref = GWT.getModuleBaseForStaticFiles() + "images/switch.svg";
		imageHref = "images/switch.svg";
		shape = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);
		setHarole("Switch");
		setupEventHandler();
		
	}
}
