package com.tranhoangdai.korengui.client.ui.svg;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGGElement;

import com.tranhoangdai.korengui.client.model.GeneralModel;
import com.tranhoangdai.korengui.client.model.ModelWithId;

public abstract class AbstractElementSvg extends OMSVGGElement{
	
	public abstract OMSVGElement getShape();
	public abstract OMSVGElement getText();
	public abstract ModelWithId getModel();
	public abstract void formElement();
	public abstract int getX();
	public abstract int getY();
	public abstract void setX( int x);
	public abstract void setY(int y);
	
}
