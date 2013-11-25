package com.tranhoangdai.korengui.client.interf;

import com.tranhoangdai.korengui.client.model.node.Node;
import com.tranhoangdai.korengui.client.model.node.zoom.ZoomableNode;

public interface GuiEventNotifier {
	
	void eventGlobalTopology();
	void eventCreateNewZoomNode(ZoomableNode zoomNode);
	void eventGetPathFlow(Node node);
	
	
}
