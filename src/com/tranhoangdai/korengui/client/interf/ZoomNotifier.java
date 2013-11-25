package com.tranhoangdai.korengui.client.interf;

import java.util.Map;

import com.tranhoangdai.korengui.client.model.Node;
import com.tranhoangdai.korengui.client.model.Link;

public interface ZoomNotifier {
	
	void zoomIn(Node zoomNode);
	Node getZoomNode();
	
	
}
