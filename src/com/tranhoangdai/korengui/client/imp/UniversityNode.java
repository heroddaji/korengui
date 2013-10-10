package com.tranhoangdai.korengui.client.imp;



import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.ui.ExternalSVGResource;
import org.vectomatic.dom.svg.ui.SVGResource;

import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;

public class UniversityNode extends Node{

	
	
	
	public UniversityNode(int id, String name) {
		super(id, name);	
		
		OMSVGImageElement 	image = new OMSVGImageElement(0, 0, 200, 200, "switch.svg");
	}

	
}
