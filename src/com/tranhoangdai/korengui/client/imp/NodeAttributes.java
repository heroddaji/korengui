package com.tranhoangdai.korengui.client.imp;

public class NodeAttributes {

	boolean supportsOfppFlood;
	boolean supportsNxRole;
    int fastWildcards;
    boolean supportsOfppTable;
    
    public NodeAttributes(){
    	
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

	public int getFastWildcards() {
		return fastWildcards;
	}

	public void setFastWildcards(int fastWildcards) {
		this.fastWildcards = fastWildcards;
	}

	public boolean isSupportsOfppTable() {
		return supportsOfppTable;
	}

	public void setSupportsOfppTable(boolean supportsOfppTable) {
		this.supportsOfppTable = supportsOfppTable;
	}
    
}
