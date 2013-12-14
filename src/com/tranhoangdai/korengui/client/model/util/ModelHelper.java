package com.tranhoangdai.korengui.client.model.util;

import java.util.HashMap;
import java.util.Map;

import com.tranhoangdai.korengui.client.model.AttachmentPoint;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;

public class ModelHelper {

	public static Map<String, Host> getChildHostsOfSourceSwitch(Switch zoomSwitch, Map<String, Host> hosts) {
		Map<String, Host> childHosts = new HashMap<String, Host>();
		for (Host host : hosts.values()) {
			for (AttachmentPoint ap : host.getAttachmentPoints()) {
				if (ap.getSwitchDPID().equals(zoomSwitch.getDpid())) {
					childHosts.put(host.getMac().get(0), host);
				}
			}
		}
		return childHosts;
	}

	public static Map<Integer, Link> getLinksOfSourceSwitch(Switch zoomSwitchModel, Map<String, Host> topologyHosts) {
		Map<String, Host> childHosts = ModelHelper.getChildHostsOfSourceSwitch(zoomSwitchModel, topologyHosts);
		Map<Integer, Link> links = new HashMap<Integer, Link>();
		for (Host h : childHosts.values()) {
			for(AttachmentPoint ap : h.getAttachmentPoints()){
				Link newLink = new Link(h.getId(), 1 , zoomSwitchModel.getId(), ap.getPort());
				links.put(newLink.getIntId(), newLink);
			}
		}

		return links;
	}
	
}
