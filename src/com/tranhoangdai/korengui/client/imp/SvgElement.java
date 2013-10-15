package com.tranhoangdai.korengui.client.imp;

import java.io.Serializable;

import org.vectomatic.dom.svg.OMSVGElement;

public abstract class SvgElement implements Serializable{
	public SvgElement(){
		
	}
	public abstract OMSVGElement getShape();	
}
