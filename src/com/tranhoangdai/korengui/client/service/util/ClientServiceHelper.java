package com.tranhoangdai.korengui.client.service.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.service.TopologyService;
import com.tranhoangdai.korengui.client.service.TopologyServiceAsync;

/**
 * This class manages interaction between client and server
 * 
 * @param <T>
 * 
 */

@SuppressWarnings("unchecked")
public class ClientServiceHelper {

	public static ClientServiceHelper INSTANCE = GWT.create(ClientServiceHelper.class);

	interface CountToAct {
		void count();
	}

	Map<String, Switch> topologySwitches = new HashMap<String, Switch>();
	Map<Integer, Link> topologyLinks = new HashMap<Integer, Link>();
	Map<String, Host> topologyHosts = new HashMap<String, Host>();

	private ClientServiceHelper() {
	}

	public <N, L, H> void getGlobalTopology(final ClientServiceAsync<N, L, H> callback) {

		CountToAct counter = new CountToAct() {
			int n = 0;
			int limit = 3;

			@Override
			public void count() {
				if (++n == limit) {
					callback.onSuccess((N) topologySwitches, (L) topologyLinks, (H) topologyHosts);
				}
			}
		};

		if (topologySwitches.size() > 0 && topologyLinks.size() > 0 && topologyHosts.size() > 0) {
			callback.onSuccess((N) topologySwitches, (L) topologyLinks, (H) topologyHosts);
		} else {
			downloadTopologySwitches(counter);
			downloadTopologyLinks(counter);
			downloadTopologyHost(counter);
		}
	}

	private void downloadTopologySwitches(final CountToAct c) {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				topologySwitches = JSONSerializationHelper.INSTANCE.createSwitches(result);
				c.count();
				System.out.println(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				System.err.println(caught);

			}
		};

		topo.getTopologySwitches(callback);
	}

	private void downloadTopologyLinks(final CountToAct c) {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {				
				topologyLinks = JSONSerializationHelper.INSTANCE.createLinks(result);
				c.count();
				System.out.println(result);
				
			}

			@Override
			public void onFailure(Throwable caught) {
				System.err.println(caught);

			}
		};

		topo.getTopologyLinks(callback);
	}

	private void downloadTopologyHost(final CountToAct c) {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);
		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {				
				topologyHosts = JSONSerializationHelper.INSTANCE.createHosts(result);
				c.count();
				System.out.println(result);
				
			}

			@Override
			public void onFailure(Throwable caught) {
				System.err.println(caught);

			}
		};

		topo.getTopologyHosts(callback);
	}
}
