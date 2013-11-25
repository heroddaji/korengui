package com.tranhoangdai.korengui.client.model.node;

public class NodePort {
	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
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

	public int getConfig() {
		return config;
	}

	public void setConfig(int config) {
		this.config = config;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCurrentFeatures() {
		return currentFeatures;
	}

	public void setCurrentFeatures(int currentFeatures) {
		this.currentFeatures = currentFeatures;
	}

	public int getAdvertisedFeatures() {
		return advertisedFeatures;
	}

	public void setAdvertisedFeatures(int advertisedFeatures) {
		this.advertisedFeatures = advertisedFeatures;
	}

	public int getSupportedFeatures() {
		return supportedFeatures;
	}

	public void setSupportedFeatures(int supportedFeatures) {
		this.supportedFeatures = supportedFeatures;
	}

	public int getPeerFeatures() {
		return peerFeatures;
	}

	public void setPeerFeatures(int peerFeatures) {
		this.peerFeatures = peerFeatures;
	}

	int portNumber;
	String hardwareAddress;
	String name;
	int config;
	int state;
	int currentFeatures;
	int advertisedFeatures;
	int supportedFeatures;
	int peerFeatures;
	
	public NodePort(){
		
	}
	
//	"portNumber": 3,
//    "hardwareAddress": "00:22:64:22:0c:6f",
//    "name": "eth3",
//    "config": 0,
//    "state": 512,
//    "currentFeatures": 672,
//    "advertisedFeatures": 1727,
//    "supportedFeatures": 703,
//    "peerFeatures": 0
}
