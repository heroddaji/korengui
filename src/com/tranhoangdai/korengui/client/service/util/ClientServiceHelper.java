package com.tranhoangdai.korengui.client.service.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tranhoangdai.korengui.client.controller.GlobalTopologyEvenController;
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

				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();

				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject jobj = array.get(i).isObject();
						// createNode(jobj);
					}

					c.count();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load nodes");

			}
		};

		topo.getTopologySwitches(callback);
	}

	private void downloadTopologyLinks(final CountToAct c) {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {

				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();
				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject obj = array.get(i).isObject();
						String srcIp = obj.get("src-switch").isString().stringValue();
						int srcport = (int) obj.get("src-port").isNumber().doubleValue();
						String dstIp = obj.get("dst-switch").isString().stringValue();
						int dstport = (int) obj.get("dst-port").isNumber().doubleValue();
						Link link = new Link(srcIp, srcport, dstIp, dstport);
						
						topologyLinks.put(link.getId(), link);
					}

					c.count();

				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load links");

			}
		};

		topo.getTopologyLinks(callback);
	}

	private void downloadTopologyHost(final CountToAct c) {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				c.count();
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load links");

			}
		};

		topo.getTopologyHosts(callback);
	}
}
