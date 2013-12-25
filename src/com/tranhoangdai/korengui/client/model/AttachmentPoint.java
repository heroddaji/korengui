package com.tranhoangdai.korengui.client.model;

public class AttachmentPoint extends GeneralModel{
	String switchDPID;
	int port;
	String errorStatus;
	
	public String getSwitchDPID() {
		return switchDPID;
	}
	public void setSwitchDPID(String switchDPID) {
		this.switchDPID = switchDPID;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	
}
