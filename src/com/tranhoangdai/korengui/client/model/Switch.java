package com.tranhoangdai.korengui.client.model;

import java.util.ArrayList;
import java.util.List;

public class Switch  extends ModelWithId {
	
	protected List<Port> ports = new ArrayList<Port>();
	protected Attributes attributes;
	protected Description description;	
	protected double action;
	protected double buffers;
	protected double capabilities;
	protected String inetAddress;
	protected double connectedSince;
	protected String dpid;
	protected String harole;
	

	@Override
	public String callMethod(String methodName) {
		String result = super.callMethod(methodName);
		if (result != "") {
			return result;
		}

		if (methodName.equals(ModelWithId.SWITCH_GETROLE)) {
			result = getHarole();
		}
		
		return result;
	}
	
	public String getId(){
		return getDpid();
	}
	
	public List<Port> getPorts() {
		return ports;
	}
	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
	public double getAction() {
		return action;
	}
	public void setAction(double action) {
		this.action = action;
	}
	public double getBuffers() {
		return buffers;
	}
	public void setBuffers(double buffers) {
		this.buffers = buffers;
	}
	public double getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(double capabilities) {
		this.capabilities = capabilities;
	}
	public String getInetAddress() {
		return inetAddress;
	}
	public void setInetAddress(String inetAddress) {
		this.inetAddress = inetAddress;
	}
	public double getConnectedSince() {
		return connectedSince;
	}
	public void setConnectedSince(double connectedSince) {
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
