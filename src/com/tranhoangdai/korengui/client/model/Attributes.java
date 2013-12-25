package com.tranhoangdai.korengui.client.model;

public class Attributes extends GeneralModel {

	boolean supportsOfppFlood;
	boolean supportsNxRole;
    double fastWildcards;
    boolean supportsOfppTable;
    
    public Attributes(){
    	
    }

	public boolean isSupportsOfppFlood() {
		return supportsOfppFlood;
	}

	public void setSupportsOfppFlood(boolean supportsOfppFlood) {
		this.supportsOfppFlood = supportsOfppFlood;
	}

	public boolean isSupportsNxRole() {
		return supportsNxRole;
	}

	public void setSupportsNxRole(boolean supportsNxRole) {
		this.supportsNxRole = supportsNxRole;
	}

	public double getFastWildcards() {
		return fastWildcards;
	}

	public void setFastWildcards(double b) {
		this.fastWildcards = b;
	}

	public boolean isSupportsOfppTable() {
		return supportsOfppTable;
	}

	public void setSupportsOfppTable(boolean supportsOfppTable) {
		this.supportsOfppTable = supportsOfppTable;
	}
    
}
