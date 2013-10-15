package com.tranhoangdai.korengui.client.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.vectomatic.dom.svg.OMSVGElement;

import com.google.gwt.event.dom.client.MouseEvent;

public abstract class Node extends SvgElement {

	int WIDTH = 40;
	int HEIGHT = 40;
	
	HashMap<String, NodeLink> paths = new HashMap<String, NodeLink>();
	List<NodePort> ports = new ArrayList<NodePort>();
	NodeAttributes attributes;
	NodeDescription description;

	float x;	
	float y;
	
	float scaleFactor = 1.5f;	
	
	int action;
	int buffers;
	int capabilities;
	String inetAddress;
	long connectedSince;
	String dpid;
	String harole;	

	public NodeAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(NodeAttributes attributes) {
		this.attributes = attributes;
	}

	public NodeDescription getDescription() {
		return description;
	}

	public void setDescription(NodeDescription description) {
		this.description = description;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getBuffers() {
		return buffers;
	}

	public void setBuffers(int buffers) {
		this.buffers = buffers;
	}

	public int getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(int capabilities) {
		this.capabilities = capabilities;
	}

	public String getInetAddress() {
		return inetAddress;
	}

	public void setInetAddress(String inetAddress) {
		this.inetAddress = inetAddress;
	}

	public long getConnectedSince() {
		return connectedSince;
	}

	public void setConnectedSince(long connectedSince) {
		this.connectedSince = connectedSince;
	}

	public String getDpid() {
		return dpid;
	}

	public void setDpid(String dpid) {
		this.dpid = dpid;
	}

	public String getHarole() {
		return harole;
	}

	public void setHarole(String harole) {
		this.harole = harole;
	}
	

	public Node() {
	}

	public Node(String ipAddress, float x, float y) {
		this.x = x;
		this.y = y;
		this.dpid = ipAddress;
	}
	
	
	public List<NodePort> getPorts() {
		return ports;
	}
	
	public void addPort(NodePort port) {
		ports.add(port);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}


	public abstract OMSVGElement getTextShape();

	public abstract void move(MouseEvent<?> event);

	public void calculateForces() {

	}

}
