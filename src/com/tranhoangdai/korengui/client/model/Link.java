package com.tranhoangdai.korengui.client.model;

import java.util.Map;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLineElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.utils.SVGConstants;

public class Link {

	private static int UNIQUEID = -1;
	int id;
	String srcSwitch;
	int srcPort;
	String dstSwitch;
	int dstPort;
	String type;
	String direction;

	Switch startNode = null;
	Switch endNode = null;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Switch getStartNode() {
		return startNode;
	}

	public void setStartNode(Switch startNode) {
		this.startNode = startNode;
	}

	public Switch getEndNode() {
		return endNode;
	}

	public void setEndNode(Switch endNode) {
		this.endNode = endNode;
	}

}