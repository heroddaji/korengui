package com.tranhoangdai.korengui.client.interf;

import java.util.Map;

import com.tranhoangdai.korengui.client.imp.Node;
import com.tranhoangdai.korengui.client.imp.NodeLink;

public interface Zoomable {
	
	void zoomIn(Map<String, Node> nodes, Map<Integer, NodeLink> links);
	void zoomOut();
	
}
