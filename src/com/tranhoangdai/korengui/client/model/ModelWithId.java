package com.tranhoangdai.korengui.client.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelWithId extends GeneralModel {
	public static final String MODEL_GETID = "ID";
	public static final String LINK_GETSRCID = "Src-switch";
	public static final String LINK_GETSRCPORT = "Src-port";
	public static final String LINK_GETDSTID = "Dst-switch";
	public static final String LINK_GETDSTPORT = "Dst-port";

	public static final String SWITCH_GETROLE = "Role";
	public static final String SWITCH_ACTION = "Action";
	public static final String SWITCH_BUFFERS = "Buffers";
	public static final String SWITCH_CAPABILITIES = "Capabilities";
	public static final String SWITCH_INETADDRESS = "INetAddress";

	public static final String HOST_IPV4 = "IPv4";
	public static final String HOST_VLAN = "VLan";
	public static final String HOST_LASTSEEN = "Last seen";
	public static final String HOST_DHCPCLIENTNAME = "DHCP name";

	public String getId() {
		return "00:00";
	}

	public int getIntId() {
		return 0;
	}

	public String callMethod(String methodName) {
		String result = "";
		//use this hack to overcome reflection problem in the ModelCellTable class
		if (methodName.equals(MODEL_GETID)) {
			return getId();
		}
		return result;
	}
}
