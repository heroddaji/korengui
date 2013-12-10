package com.tranhoangdai.korengui.client.view;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;

public class AbstractPanel extends TabLayoutPanel {
	
	protected Map<String, Switch> topologySwitches = new HashMap<String, Switch>();
	protected Map<Integer, Link> topologyLinks = new HashMap<Integer, Link>();
	protected Map<String, Host> topologyHosts = new HashMap<String, Host>();

	public AbstractPanel(double barHeight, Unit barUnit) {
		super(barHeight, barUnit);
	}
	
	public void setModelInformation(Map<String, Switch> switches, Map<Integer, Link> links, Map<String, Host> hosts){
		this.topologySwitches = switches;
		this.topologyHosts = hosts;
		this.topologyLinks = links;
	}

	public Map<String, Switch> getTopologySwitchModels() {
		return topologySwitches;
	}

	public Map<Integer, Link> getTopologyLinkModels() {
		return topologyLinks;
	}

	public Map<String, Host> getTopologyHostModels() {
		return topologyHosts;
	}
}
