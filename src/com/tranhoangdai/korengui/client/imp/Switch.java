package com.tranhoangdai.korengui.client.imp;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;

import com.google.gwt.core.client.GWT;

public class Switch extends Node {

	String imageHref = GWT.getModuleBaseURL() + "images/switch.svg";
	OMSVGImageElement image;

	public Switch(String ip, int x, int y) {
		super(ip, x, y);
		image = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);
	}

	@Override
	public OMSVGElement getShape() {
		return image;
	}
}
