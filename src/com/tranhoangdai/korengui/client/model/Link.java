package com.tranhoangdai.korengui.client.model;

import java.util.Map;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLineElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.utils.SVGConstants;

public class Link {

	private static int UNIQUEID = -1;
	OMSVGLineElement line = null;
	OMSVGTextElement textShapeSrcPort;
	OMSVGTextElement textShapeDstPort;
	
	Node startNode = null;
	Node endNode = null;
	
	int id;

	String srcSwitch;
	int srcPort;
	public Node getStartNode() {
		return startNode; 
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}

	public String getSrcSwitch() {
		return srcSwitch;
	}

	public void setSrcSwitch(String srcSwitch) {
		this.srcSwitch = srcSwitch;
	}

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public String getDstSwitch() {
		return dstSwitch;
	}

	public void setDstSwitch(String dstSwitch) {
		this.dstSwitch = dstSwitch;
	}

	public int getDstPort() {
		return dstPort;
	}

	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	String dstSwitch;
	int dstPort;
	String type;
	String direction;

	public Link(String srcSwitch, int srcPort, String dstSwitch, int dstPort) {
		id = ++UNIQUEID;
		this.srcSwitch = srcSwitch;
		this.srcPort = srcPort;
		this.dstSwitch = dstSwitch;
		this.dstPort = dstPort;
	}
	public Link(Link link) {
		this.id = link.getId();
		this.srcSwitch = link.getSrcSwitch();
		this.srcPort = link.getSrcPort();
		this.dstSwitch = link.getDstSwitch();
		this.dstPort = link.getDstPort();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	

}
