package com.tranhoangdai.korengui.client.model;

public abstract class ModelWithId extends GeneralModel {
	public static final String MODEL_GETIDMETHOD = "ID";
	public static final String LINK_GETSRCPORT = "LINK_GETSRCPORT";
	public static final String LINK_GETSRCID = "LINK_GETSRCID";
	public static final String LINK_GETDSTPORT = "LINK_GETDSTPORT";
	public static final String LINK_GETDSTID= "LINK_GETDSTID";
	public static final String SWITCH_GETROLE= "SWITCH_GETROLE";
	
	

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
