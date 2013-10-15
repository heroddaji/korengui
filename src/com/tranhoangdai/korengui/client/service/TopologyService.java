package com.tranhoangdai.korengui.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tranhoangdai.korengui.client.imp.Dummy;

@RemoteServiceRelativePath("topology")
public interface TopologyService extends RemoteService {
	
	String getTopologySwitches();
	Dummy dummy();
	String getTopologyLinks();
	

}
