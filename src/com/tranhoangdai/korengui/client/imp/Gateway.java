package com.tranhoangdai.korengui.client.imp;



import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.ui.ExternalSVGResource;
import org.vectomatic.dom.svg.ui.SVGResource;
import org.vectomatic.dom.svg.utils.OMSVGParser;
import org.vectomatic.dom.svg.utils.SVGConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;

public class Gateway extends Node{
	
	String imageHref = GWT.getModuleBaseURL() + "images/router.svg";
	OMSVGImageElement image;
	public Gateway(String ip, int x, int y) {
		super(ip,x,y);
	
		image = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);
		
		setupEventHandler();
		
	}
	
	private void setupEventHandler(){
		image.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				OMSVGDocument doc = OMSVGParser.currentDocument();
				image.getX().getBaseVal().setValue(300);
			}
		});
	}
	
	@Override
	public OMSVGElement getShape() {		
		return image;		
	}
}
