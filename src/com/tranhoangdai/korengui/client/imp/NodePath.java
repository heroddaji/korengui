package com.tranhoangdai.korengui.client.imp;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLineElement;
import org.vectomatic.dom.svg.OMSVGPoint;
import org.vectomatic.dom.svg.utils.SVGConstants;

public class NodePath extends SvgElement {

	static int INCREASEID = -1;

	OMSVGLineElement line = null;
	Node startNode = null;
	Node endNode = null;
	int id = -1;

	public NodePath(Node startNode, Node endNode) {
		id = ++INCREASEID;
		this.startNode = startNode;
		this.endNode = endNode;

		startNode.addPath(this);
		endNode.addPath(this);

		adjust();
	}

	public void adjust() {
		if (startNode == null || endNode == null) {
			return;
		}

		float x1 = ((OMSVGImageElement) startNode.getShape()).getX().getBaseVal().getValue();
		float y1 = ((OMSVGImageElement) startNode.getShape()).getY().getBaseVal().getValue();
		float width1 = ((OMSVGImageElement) startNode.getShape()).getWidth().getBaseVal().getValue();
		float height1 = ((OMSVGImageElement) startNode.getShape()).getHeight().getBaseVal().getValue();

		float x2 = ((OMSVGImageElement) endNode.getShape()).getX().getBaseVal().getValue();
		float y2 = ((OMSVGImageElement) endNode.getShape()).getY().getBaseVal().getValue();
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

	public int getId() {
		return id;
	}

	@Override
	public OMSVGElement getShape() {
		return line;
	}

}
