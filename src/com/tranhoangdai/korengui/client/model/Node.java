package com.tranhoangdai.korengui.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGTextElement;

import com.google.gwt.event.dom.client.MouseEvent;

public abstract class Node extends SvgElement implements Cloneable {

	int WIDTH = 60;
	int HEIGHT = 60;
	
	HashMap<Integer, NodeLink> localLinks = new HashMap<Integer, NodeLink>();
	List<NodePort> ports = new ArrayList<NodePort>();
	NodeAttributes attributes;
	NodeDescription description;
	OMSVGTextElement textShape;
	int x;	
	int y;
	
	int scaleFactor = 2;	
	
	int action;
	int buffers;
	int capabilities;
	String inetAddress;
	long connectedSince;
	String dpid;
	String harole;	
	
	
	public Node(Node node){
		setX(node.getX());
		setY(node.getY());
	}

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

	public Node(String ipAddress, int x, int y) {
		this.x = x;
		this.y = y;
		this.dpid = ipAddress;
	}
	
	public void addLink(NodeLink link){
		localLinks.put(link.getId(), link);
	}
	
	public List<NodePort> getPorts() {
		return ports;
	}
	
	public void addPort(NodePort port) {
		ports.add(port);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public OMSVGElement getTextShape(){
		return textShape;
	}

	public abstract void move(MouseEvent<?> event);

	public void calculateForces() {

	}

	public abstract OMSVGElement getGroupShape();
	
	
	 

}
