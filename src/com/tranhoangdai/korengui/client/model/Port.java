package com.tranhoangdai.korengui.client.model;

public class Port extends GeneralModel{
	
	double portNumber;
	String hardwareAddress;
	String name;
	double config;
	double state;
	double currentFeatures;
	double advertisedFeatures;
	double supportedFeatures;
	double peerFeatures;
	
	
//	"portNumber": 3,
//    "hardwareAddress": "00:22:64:22:0c:6f",
//    "name": "eth3",
//    "config": 0,
//    "state": 512,
//    "currentFeatures": 672,
//    "advertisedFeatures": 1727,
//    "supportedFeatures": 703,
//    "peerFeatures": 0
	
	public double getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(double portNumber) {
		this.portNumber = portNumber;
	}

	public String getHardwareAddress() {
		return hardwareAddress;
	}

	public void setHardwareAddress(String hardwareAddress) {
		this.hardwareAddress = hardwareAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getConfig() {
		return config;
	}

	public void setConfig(double config) {
		this.config = config;
	}

	public double getState() {
		return state;
	}

	public void setState(double state) {
		this.state = state;
	}

	public double getCurrentFeatures() {
		return currentFeatures;
	}

	public void setCurrentFeatures(double currentFeatures) {
		this.currentFeatures = currentFeatures;
	}

	public double getAdvertisedFeatures() {
		return advertisedFeatures;
	}

	public void setAdvertisedFeatures(double advertisedFeatures) {
		this.advertisedFeatures = advertisedFeatures;
	}

	public double getSupportedFeatures() {
		return supportedFeatures;
	}

	public void setSupportedFeatures(double supportedFeatures) {
		this.supportedFeatures = supportedFeatures;
	}

	public double getPeerFeatures() {
		return peerFeatures;
	}

	public void setPeerFeatures(double peerFeatures) {
		this.peerFeatures = peerFeatures;
	}

	
}
