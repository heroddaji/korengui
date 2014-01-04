package com.tranhoangdai.korengui.client.model;

import java.util.List;

public class Host extends ModelWithId {
	String entityClass;
	List<String> mac;
	List<String> ipv4;
	List<String> vlan;
	double lastSeen;
	String dhcpClientName;
	List<AttachmentPoint> attachmentPoints;

	/*
	 * public static final String HOST_IPV4= "IPv4"; public static final String
	 * HOST_VLAN= "VLan"; public static final String HOST_LASTSEEN= "Last seen";
	 * public static final String HOST_DHCPCLIENTNAME= "DHCP name";
	 */

	@Override
	public String callMethod(String methodName) {
		String result = super.callMethod(methodName);
		if (result != "") {
			return result;
		}

		if (methodName.equals(ModelWithId.HOST_IPV4)) {
			if (ipv4.size() > 0) {
				result = getIpv4().get(0);
			}
		}
		if (methodName.equals(ModelWithId.HOST_DHCPCLIENTNAME)) {
			result = getDhcpClientName();
		}
		if (methodName.equals(ModelWithId.HOST_VLAN)) {
			if (vlan.size() > 0) {
				result = getVlan().get(0);
			}
		}
		if (methodName.equals(ModelWithId.HOST_LASTSEEN)) {
			result = String.valueOf(getLastSeen());
		}

		return result;
	}

	public String getId() {
		return getMacAddress();
	}

	public String getMacAddress() {
		return mac.get(0);
	}

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
