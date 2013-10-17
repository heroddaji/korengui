package com.tranhoangdai.korengui.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TopologyServiceAsync {

	void getTopologySwitches(AsyncCallback<String> callback);

	void getTopologyLinks(AsyncCallback<String> callback);

}
