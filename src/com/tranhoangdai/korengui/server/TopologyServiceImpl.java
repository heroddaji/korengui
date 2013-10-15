package com.tranhoangdai.korengui.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tranhoangdai.korengui.client.imp.Dummy;
import com.tranhoangdai.korengui.client.service.TopologyService;

public class TopologyServiceImpl extends RemoteServiceServlet implements TopologyService{

	@Override
	public String getTopologyLinks() {
		String inputLine = "" ;
		String inputLine2 = "" ;
		try {
			URL topologyUrl = new URL("http://163.180.140.95:8080/wm/topology/links/json");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(topologyUrl.openStream()));
			while((inputLine = reader.readLine()) != null){
				inputLine2 += inputLine;
			}
			reader.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputLine2;
		
	}
	
	public String getTopologySwitches(){
		String inputLine = "" ;
		String inputLine2 = "" ;
		try {
			URL topologyUrl = new URL("http://163.180.140.95:8080/wm/core/controller/switches/json");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(topologyUrl.openStream()));
			while((inputLine = reader.readLine()) != null){
				inputLine2 += inputLine;
			}
			reader.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputLine2;
		
	}
	
	public Dummy dummy() {
		Dummy dum = new Dummy();
		//dum.setEm("haha");
		return dum;
	}

}
