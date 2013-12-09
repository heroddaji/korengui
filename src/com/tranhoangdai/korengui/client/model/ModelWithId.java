package com.tranhoangdai.korengui.client.model;

public abstract class ModelWithId extends GeneralModel {
	public static final String MODEL_GETIDMETHOD = "ID";	
	public static final String LINK_GETSRCID = "Src-switch";
	public static final String LINK_GETSRCPORT = "Src-port";	
	public static final String LINK_GETDSTID= "Dst-switch";
	public static final String LINK_GETDSTPORT = "Dst-port";
	public static final String SWITCH_GETROLE= "Role";
	
	

	public String getId() {
		return "00:00";
	}
	
	public int getIntId() {
		return 0;
	}
	
	public String callMethod(String methodName){
		//use this hack to overcome reflection problem in the ModelCellTable class
		if(methodName.equals(MODEL_GETIDMETHOD)){
			return getId();
		}
		return null;
	}
}
