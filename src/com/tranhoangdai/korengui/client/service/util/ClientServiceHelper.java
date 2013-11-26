package com.tranhoangdai.korengui.client.service.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Node;
import com.tranhoangdai.korengui.client.service.TopologyService;
import com.tranhoangdai.korengui.client.service.TopologyServiceAsync;

/**
 * This class manages interaction between client and server
 * @param <T>
 * 
 */
public class ClientServiceHelper {
	
	Object eventAsync;
	Map<String,Node> topologyNodes = new HashMap<>();	
	Map<Integer,Link> topologyLinks = new HashMap<>();
	Map<String,Host> topologyHosts = new HashMap<>();

	public <T> void downloadGlobalTopology(ClientServiceAsync<T> callback) {
		eventAsync = callback;		
		if (topologyNodes.size() > 0 && topologyLinks.size() > 0) {
			callback.onSuccess(result);
		} else {
			downloadTopologySwitches();
		}
	}

	public void downloadTopologySwitches() {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {

				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();

				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject jobj = array.get(i).isObject();
						createNode(jobj);
					}
					// finish download nodes info, now download links info
					downloadTopologyLinks();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load nodes");

			}
		};

		topo.getTopologySwitches(callback);
	}

	public void downloadTopologyLinks() {
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

						link.findAndMatchNode(globalNodes);
						globalLinks.put(link.getId(), link);
					}

					fakeChildLink();

					// finish download links, notify finish download event
					notifyFinishDownloadGlobalTopology();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load links");

			}
		};

		topo.getTopologyLinks(callback);
	}
}
