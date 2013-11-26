package com.tranhoangdai.korengui.client.view.svg;

import java.util.Map;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLineElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.utils.SVGConstants;

import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;

public class LinkSvg extends AbstractElementSvg{
	
	Link linkModel = null;
	NodeSvg startNode = null;
	NodeSvg endNode = null;
	OMSVGTextElement textShapeSrcPort;
	OMSVGTextElement textShapeDstPort;	
	OMSVGLineElement line = null;
		
	public LinkSvg(Link model) {		

		linkModel = model;
	}
	
	


	public void findAndMatchNode(Map<String, Switch> nodes) {
//		startNode = nodes.get(linkModel.getSrcSwitch());
//		if (startNode != null) {
//			startNode.addLink(this);
//		}
//		endNode = nodes.get(dstSwitch);
//		if (endNode != null) {
//			endNode.addLink(this);
//		}
	}

	public void adjust() {
		if (startNode == null || endNode == null) {
			return;
		}

		float x1 = startNode.getX();
		float y1 = startNode.getY();		
		float width1 = ((OMSVGImageElement) startNode.getShape()).getWidth().getBaseVal().getValue();
		float height1 = ((OMSVGImageElement) startNode.getShape()).getHeight().getBaseVal().getValue();
		

		float x2 = endNode.getX();
		float y2 = endNode.getY();
		float width2 = ((OMSVGImageElement) endNode.getShape()).getWidth().getBaseVal().getValue();
		float height2 = ((OMSVGImageElement) endNode.getShape()).getHeight().getBaseVal().getValue();

		if (line == null) {
			line = new OMSVGLineElement(x1, y1, x2, y2);
			line.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);
		}

		line.getX1().getBaseVal().setValue(x1 + width1 / 2);
		line.getY1().getBaseVal().setValue(y1 + height1 / 2);
		line.getX2().getBaseVal().setValue(x2 + width2 / 2);
		line.getY2().getBaseVal().setValue(y2 + height2 / 2);

	}

	@Override
	public OMSVGElement getShape() {
		return line;
	}




	@Override
	public OMSVGElement getText() {
		// TODO Auto-generated method stub
		return null;
	}

}
