package com.tranhoangdai.korengui.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.web.bindery.requestfactory.vm.impl.Deobfuscator;
import com.tranhoangdai.korengui.client.service.TopologyService;

@SuppressWarnings("unused")
public class TopologyServiceImpl extends RemoteServiceServlet implements TopologyService {

	//"http://163.180.118.215:8080/wm/core/controller/switches/json";
	//String host = "http://localhost";
	//String port = "9876";
	//String host = "http://163.180.118.215";
	String host = "hkjhkjhkj";
	String port = "1234";
	String switchApi = "/wm/core/controller/switches/json";
	String linkApi = "/wm/topology/links/json";
	String hostApi = "/wm/device/";
	String pathApi = "/wm/haha";
	String fileSwitches = "sample-json/nodes.json";
	String fileLinks = "sample-json/links.json";
	String fileDevices = "sample-json/devices.json";
	String filePath = "sample-json/paths.json";

	@Override
	public String getTopologySwitches() {
		
		String json = "";
		String url = host + ":" + port + switchApi;
		json = readUrl(url);
		return json;
	}

	@Override
	public String getTopologyLinks() {
		String json = "";
		String url = host + ":" + port + linkApi;
		json = readUrl(url);
		return json;
	}

	public String getTopologyHosts() {
		String json = "";
		String url = host + ":" + port + hostApi;
		json = readUrl(url);
		return json;
	}

	@Override
	public String getPathFlow(String nodeId1, String nodeId2) {
		//fake result, get whole link, then return first 2-3 links
		String json = "";
		String url = host + ":" + port + linkApi;
		json = readUrl(url);
		return json;
	}

	public String readUrl(String url) {

		String inputLine = "";
		String inputLine2 = "";

		URL topologyUrl;
		try {
			topologyUrl = new URL(url);

			BufferedReader reader = new BufferedReader(new InputStreamReader(topologyUrl.openStream()));
			while ((inputLine = reader.readLine()) != null) {
				inputLine2 += inputLine;
			}
			reader.close();
		} catch (Exception e) {

			//cheating, read from file
			if (url.contains(switchApi)) {
				return readFile(fileSwitches);
			}

			if (url.contains(linkApi)) {
				return readFile(fileLinks);
			}

			if (url.contains(hostApi)) {
				return readFile(fileDevices);
			}
			
			if (url.contains(pathApi)) {
				return readFile(filePath);
			}
		}

		return inputLine2;
	}

	public String readFile(String filePath) {
		
		ServletContext servletContext = getServletContext();		
		String path = servletContext.getRealPath("/");		
		filePath = path + filePath;
		System.out.println("Read files:" + filePath);
		String inputLine = "";
		String inputLine2 = "";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));

			while ((inputLine = reader.readLine()) != null) {
				inputLine2 += inputLine;
			}
			reader.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		return inputLine2;
	}
}
