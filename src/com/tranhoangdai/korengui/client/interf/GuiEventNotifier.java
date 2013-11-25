package com.tranhoangdai.korengui.client.interf;

import com.tranhoangdai.korengui.client.model.Node;
import com.tranhoangdai.korengui.client.view.svg.ZoomableNode;

public interface GuiEventNotifier {
	
	void eventGlobalTopology();
	void eventCreateNewZoomNode(ZoomableNode zoomNode);
	void eventGetPathFlow(Node node);
	
	
}
