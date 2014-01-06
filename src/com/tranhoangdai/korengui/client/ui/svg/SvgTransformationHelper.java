package com.tranhoangdai.korengui.client.ui.svg;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTransform;
import org.vectomatic.dom.svg.OMSVGTransformList;
import org.vectomatic.dom.svg.utils.OMSVGParser;

public class SvgTransformationHelper {

	public static OMSVGGElement scaleUp(OMSVGGElement svgElement, float scaleX, float scaleY, float scaleFactor) {

		OMSVGTransform t1 = null;
		OMSVGTransform t2 = null;
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		
		
		t1 = svg.createSVGTransform();
		t2 = svg.createSVGTransform();
		OMSVGTransformList xforms = svgElement.getTransform().getBaseVal();
		xforms.appendItem(t1);
		xforms.appendItem(t2);
		t1.setTranslate(-scaleX, -scaleY);
		t2.setScale(scaleFactor, scaleFactor);

		return svgElement;
	}
	
	public static OMSVGGElement scaleDown(OMSVGGElement svgElement, float scaleX, float scaleY, float scaleFactor) {

		OMSVGTransform t1 = null;
		OMSVGTransform t2 = null;
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		t1 = svg.createSVGTransform();
		t2 = svg.createSVGTransform();
		OMSVGTransformList xforms = svgElement.getTransform().getBaseVal();
		xforms.appendItem(t2);
		xforms.appendItem(t1);
		t2.setScale(scaleFactor, scaleFactor);
		t1.setTranslate(-scaleX, -scaleY);
		return svgElement;
	}	
	
	public static void translateTo(AbstractElementSvg svgElement, int x, int y) {		
		svgElement.setX(x);
		svgElement.setY(y);
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		OMSVGTransform t;
		t = svg.createSVGTransform();
		OMSVGTransformList xforms = svgElement.getTransform().getBaseVal();
		xforms.appendItem(t);
		t.setTranslate(x, y);
	}
	
	
	
}
