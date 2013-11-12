package com.tranhoangdai.korengui.client.interf;

import com.tranhoangdai.korengui.client.imp.node.zoom.ZoomableNode;

public interface GuiEventNotifier {
	
	void eventGlobalTopology();
	void eventZoomToNode(ZoomableNode zoomNode);
	void eventGetPathFlow();
	
	
}
