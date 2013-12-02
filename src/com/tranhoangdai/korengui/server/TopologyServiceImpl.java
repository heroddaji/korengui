package com.tranhoangdai.korengui.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tranhoangdai.korengui.client.service.TopologyService;

public class TopologyServiceImpl extends RemoteServiceServlet implements TopologyService {

	//"http://163.180.118.215:8080/wm/core/controller/switches/json";
	String host = "http://localhost";
	String port = "9876";
	String switchApi = "/wm/core/controller/switches/json";
	String linkApi = "/wm/topology/links/json";
	String hostApi = "/wm/device/";
	
	@Override
	public String getTopologySwitches() {
		String json = "";
		String url = host + ":" + port + switchApi;

		try {
			json = readUrl(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// try {
		// json = readFile("sample-json/nodes.json");
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }

		return json;
	}

	@Override
	public String getTopologyLinks() {

		String json = "";
		String url = host + ":" + port + linkApi;

		try {
			json = readUrl(url);
		} catch (IOException e) {

		}

		return json;
	}

	public String getTopologyHosts() {

		String json = "";
		String url = host + ":" + port + hostApi;

		try {
			json = readUrl(url);
		} catch (IOException e) {

		}

		return json;
	}

	@Override
	public String getPathFlow(String nodeId1, String nodeId2) {

		String json = "";

		try {
			json = readFile("sample-json/paths.json");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return json;
	}

	public String readUrl(String url) throws IOException {
		String inputLine = "";
		String inputLine2 = "";

		URL topologyUrl = new URL(url);

		BufferedReader reader = new BufferedReader(new InputStreamReader(topologyUrl.openStream()));
		while ((inputLine = reader.readLine()) != null) {
			inputLine2 += inputLine;
		}
		reader.close();

		return inputLine2;
	}

	public String readFile(String filePath) throws IOException {
		String inputLine = "";
		String inputLine2 = "";

		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		while ((inputLine = reader.readLine()) != null) {
			inputLine2 += inputLine;
		}
		reader.close();

		return inputLine2;
	}

}
