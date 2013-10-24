package com.tranhoangdai.korengui.client.interf;

import java.util.Map;

import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;

public interface ZoomNotifier {
	
	void zoomIn(Map<String, Node> nodes, Map<Integer, NodeLink> links);
	void zoomOut();
	
}
