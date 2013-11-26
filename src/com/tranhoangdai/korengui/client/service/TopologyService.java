package com.tranhoangdai.korengui.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("topology")
public interface TopologyService extends RemoteService {
	
	String getTopologySwitches();
	String getTopologyLinks();
	String getTopologyHosts();
	String getPathFlow(String nodeId1, String nodeId2);
	

}
