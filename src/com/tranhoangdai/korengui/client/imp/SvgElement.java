package com.tranhoangdai.korengui.client.imp;

import java.io.Serializable;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGGElement;

public abstract class SvgElement implements Serializable{
	
	OMSVGGElement groupShape;
	
	public SvgElement(){
		
	}
	public abstract OMSVGElement getShape();	
}
