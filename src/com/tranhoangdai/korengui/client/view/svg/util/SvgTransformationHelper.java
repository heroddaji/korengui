package com.tranhoangdai.korengui.client.view.svg.util;

import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTransform;
import org.vectomatic.dom.svg.OMSVGTransformList;
import org.vectomatic.dom.svg.utils.OMSVGParser;

public class SvgTransformationHelper {

	public static OMSVGImageElement scaleUp(OMSVGImageElement shape, float centerLocation, float scaleFactor) {

		OMSVGTransform t1 = null;
		OMSVGTransform t2 = null;
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();

		t2 = svg.createSVGTransform();
		OMSVGTransformList xforms = shape.getTransform().getBaseVal();
		xforms.appendItem(t2);
		t2.setScale(scaleFactor, scaleFactor);

		return shape;
	}
	
	public static OMSVGImageElement scaleDown(OMSVGImageElement shape, float centerLocation, float scaleFactor) {

		OMSVGTransform t1 = null;
		OMSVGTransform t2 = null;
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		t2 = svg.createSVGTransform();
		OMSVGTransformList xforms = shape.getTransform().getBaseVal();
		xforms.appendItem(t2);
		t2.setScale(scaleFactor, scaleFactor);
		return shape;
	}	
	
	public void translateTo(int x, int y) {
		
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		OMSVGTransform t;
		t = svg.createSVGTransform();
	//	OMSVGTransformList xforms = getTransform().getBaseVal();
	//	xforms.appendItem(t);
		t.setTranslate(x, y);
	}
	
	
	
}
