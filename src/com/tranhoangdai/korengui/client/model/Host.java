package com.tranhoangdai.korengui.client.model;

import java.util.List;

public class Host {
	String entityClass;
	List<String> mac;
	List<String> ipv4;
	List<String> vlan;
	double lastSeen;
	String dhcpClientName;
	List<AttachmentPoint> attachmentPoints;
	
	public String getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}		
	
	public double getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(double lastSeen) {
		this.lastSeen = lastSeen;
	}
	public String getDhcpClientName() {
		return dhcpClientName;
	}
	public void setDhcpClientName(String dhcpClientName) {
		this.dhcpClientName = dhcpClientName;
	}
	public List<String> getMac() {
		return mac;
	}
	public void setMac(List<String> mac) {
		this.mac = mac;
	}
	public List<String> getIpv4() {
		return ipv4;
	}
	public void setIpv4(List<String> ipv4) {
		this.ipv4 = ipv4;
	}
	public List<String> getVlan() {
		return vlan;
	}
	public void setVlan(List<String> vlan) {
		this.vlan = vlan;
	}
	public List<AttachmentPoint> getAttachmentPoints() {
		return attachmentPoints;
	}
	public void setAttachmentPoints(List<AttachmentPoint> attachmentPoints) {
		this.attachmentPoints = attachmentPoints;
	}	
	
}
