package com.tranhoangdai.korengui.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tranhoangdai.korengui.client.imp.Dummy;
import com.tranhoangdai.korengui.client.imp.NodeLink;

public interface TopologyServiceAsync {

	void dummy(AsyncCallback<Dummy> callback);

	void getTopologySwitches(AsyncCallback<String> callback);

	void getTopologyLinks(AsyncCallback<String> callback);

}
