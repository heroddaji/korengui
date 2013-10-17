package com.tranhoangdai.korengui.client.imp;

import java.io.Serializable;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLineElement;
import org.vectomatic.dom.svg.utils.SVGConstants;

public class NodeLink extends SvgElement implements Serializable {

	private static int UNIQUEID = -1;
	OMSVGLineElement line = null;
	Node startNode = null;
	Node endNode = null;
	int id;

	String srcSwitch;
	int srcPort;
	String dstSwitch;
	int dstPort;
	String type;
	String direction;

	public NodeLink(String srcSwitch, int srcPort, String dstSwitch, int dstPort) {
		id = ++UNIQUEID;
		this.srcSwitch = srcSwitch;
		this.srcPort = srcPort;
		this.dstSwitch = dstSwitch;
		this.dstPort = dstPort;
	}

	public void findAndMatchNode(Map<String, Node> nodes) {
		startNode = nodes.get(srcSwitch);
		if (startNode != null) {
			startNode.addLink(this);
		}
		endNode = nodes.get(dstSwitch);
		if (endNode != null) {
			endNode.addLink(this);
		}
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

	@Override
	public OMSVGElement getShape() {
		return line;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
