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
	OMSVGImageElement shape;
	public Gateway(String ip, int x, int y) {
		super(ip,x,y);
	
		shape = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);		
		setupEventHandler();		
	}
	
	private void setupEventHandler(){
		shape.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				handleMouseDownEvent(event);
			}
		});
	}
	
	private void handleMouseDownEvent(MouseDownEvent event){
		OMSVGDocument doc = OMSVGParser.currentDocument();
		MapPanel.INSTANCE.setClickedNode(this);		
	}
	
	@Override
	public void move(int x, int y){
		super.move(x, y);
		shape.getX().getBaseVal().setValue(x);
		shape.getY().getBaseVal().setValue(y);
		
		for(NodePath path: paths.values()){
			path.adjust();
		}
		
	}
	
	@Override
	public OMSVGElement getShape() {		
		return shape;		
	}
}
