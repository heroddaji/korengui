package com.tranhoangdai.korengui.client.imp;

public class NodeDescription {
	String software;
	String hardware;
	String manufacturer;
	String serialNum;
	String datapath;
	
	public NodeDescription(){
		
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getHardware() {
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getDatapath() {
		return datapath;
	}

	public void setDatapath(String datapath) {
		this.datapath = datapath;
	}
	
	
//	 "software": "1.4.0+build0",
//     "hardware": "Open vSwitch",
//     "manufacturer": "Nicira Networks, Inc.",
//     "serialNum": "None",
//     "datapath": "None"
}
