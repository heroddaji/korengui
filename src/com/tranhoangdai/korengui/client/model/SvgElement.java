package com.tranhoangdai.korengui.client.model;

import java.io.Serializable;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGGElement;

public abstract class SvgElement implements Serializable{
	
	protected OMSVGGElement groupShape;
	
	public SvgElement(){
		
	}
	public abstract OMSVGElement getShape();	
}
