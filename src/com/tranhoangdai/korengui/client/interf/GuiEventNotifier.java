package com.tranhoangdai.korengui.client.interf;

import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.imp.node.zoom.ZoomableNode;

public interface GuiEventNotifier {
	
	void eventGlobalTopology();
	void eventCreateNewZoomNode(ZoomableNode zoomNode);
	void eventGetPathFlow(Node node);
	
	
}
