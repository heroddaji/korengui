package com.tranhoangdai.korengui.client.imp;

import java.util.Map;

public interface NodeEvent {
	public void gotTopology(Map<String, Node> nodes, Map<Integer, NodeLink> links);
}
