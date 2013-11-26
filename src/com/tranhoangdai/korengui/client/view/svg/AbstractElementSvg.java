package com.tranhoangdai.korengui.client.view.svg;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGGElement;

public abstract class AbstractElementSvg extends OMSVGGElement{
	
	public abstract OMSVGElement getShape();
	public abstract OMSVGElement getText();
	
}
