package com.tranhoangdai.korengui.client.interf;

import java.util.Map;

import com.tranhoangdai.korengui.client.model.link.NodeLink;
import com.tranhoangdai.korengui.client.model.node.Node;

public interface ZoomNotifier {
	
	void zoomIn(Node zoomNode);
	Node getZoomNode();
	
	
}
