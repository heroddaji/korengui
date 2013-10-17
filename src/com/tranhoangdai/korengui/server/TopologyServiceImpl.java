package com.tranhoangdai.korengui.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tranhoangdai.korengui.client.service.TopologyService;

public class TopologyServiceImpl extends RemoteServiceServlet implements TopologyService {

	@Override
	public String getTopologyLinks() {
		String sample = "[{\"src-switch\":\"00:00:00:00:00:00:00:02\",\"src-port\":3,\"dst-switch\":\"00:00:00:00:00:00:00:03\",\"dst-port\":2,\"type\":\"internal\",\"direction\":\"bidirectional\"},{\"src-switch\":\"00:00:00:00:00:00:00:02\",\"src-port\":4,\"dst-switch\":\"00:00:00:00:00:00:00:04\",\"dst-port\":2,\"type\":\"internal\",\"direction\":\"bidirectional\"},{\"src-switch\":\"00:00:00:00:00:00:00:04\",\"src-port\":1,\"dst-switch\":\"00:00:00:00:00:00:00:06\",\"dst-port\":1,\"type\":\"internal\",\"direction\":\"bidirectional\"},{\"src-switch\":\"00:00:00:00:00:00:00:01\",\"src-port\":1,\"dst-switch\":\"00:00:00:00:00:00:00:02\",\"dst-port\":1,\"type\":\"internal\",\"direction\":\"bidirectional\"},{\"src-switch\":\"00:00:00:00:00:00:00:01\",\"src-port\":3,\"dst-switch\":\"00:00:00:00:00:00:00:03\",\"dst-port\":1,\"type\":\"internal\",\"direction\":\"unidirectional\"}]";
		return sample;
		
		
//		String inputLine = "";
//		String inputLine2 = "";
//		try {
//			URL topologyUrl = new URL("http://163.180.140.95:8080/wm/topology/links/json");
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(topologyUrl.openStream()));
//			while ((inputLine = reader.readLine()) != null) {
//				inputLine2 += inputLine;
//			}
//			reader.close();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//		 return inputLine2;		

	}

	public String getTopologySwitches(){
		
		String sample = "[{\"dpid\":\"00:00:00:00:00:00:00:02\",\"dst-port\":2},{\"dpid\":\"00:00:00:00:00:00:00:03\",\"dst-port\":2},{\"dpid\":\"00:00:00:00:00:00:00:04\",\"dst-port\":2},{\"dpid\":\"00:00:00:00:00:00:00:05\",\"dst-port\":2},{\"dpid\":\"00:00:00:00:00:00:00:06\",\"dst-port\":2}]";
		
		 return sample;
		
//		String inputLine = "" ;
//		String inputLine2 = "" ;
//		try {
//			URL topologyUrl = new URL("http://163.180.140.95:8080/wm/core/controller/switches/json");
//			
//			BufferedReader reader = new BufferedReader(new InputStreamReader(topologyUrl.openStream()));
//			while((inputLine = reader.readLine()) != null){
//				inputLine2 += inputLine;
//			}
//			reader.close();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//		          		 
//		return inputLine2;
		
	}

}
