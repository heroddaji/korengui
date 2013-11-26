package com.tranhoangdai.korengui.client.model;

import java.util.ArrayList;
import java.util.List;

public class Switch  {
	
	protected List<Port> ports = new ArrayList<Port>();
	protected NodeAttributes attributes;
	protected NodeDescription description;	
	protected int action;
	protected int buffers;
	protected int capabilities;
	protected String inetAddress;
	protected long connectedSince;
	protected String dpid;
	protected String harole;
	
	public List<Port> getPorts() {
		return ports;
	}
	public void setPorts(List<Port> ports) {
		this.ports = ports;
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

}
