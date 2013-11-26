package com.tranhoangdai.korengui.client.model;

public class Host {
	String entityClass;
	String mac;
	String ipv4;
	String vlan;
	long lastSeen;
	String dhcpClientName;
	AttachmentPoint attachmentPoint;
	public String getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getIpv4() {
		return ipv4;
	}
	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}
	public String getVlan() {
		return vlan;
	}
	public void setVlan(String vlan) {
		this.vlan = vlan;
	}
	public long getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(long lastSeen) {
		this.lastSeen = lastSeen;
	}
	public String getDhcpClientName() {
		return dhcpClientName;
	}
	public void setDhcpClientName(String dhcpClientName) {
		this.dhcpClientName = dhcpClientName;
	}
	public AttachmentPoint getAttachmentPoint() {
		return attachmentPoint;
	}
	public void setAttachmentPoint(AttachmentPoint attachmentPoint) {
		this.attachmentPoint = attachmentPoint;
	}
	
	
	
	
}
