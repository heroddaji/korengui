package com.tranhoangdai.korengui.client.interf;

import java.util.Map;

import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;

public interface ZoomNotifier {
	
	void zoomIn(NodeSvg zoomNode);
	Switch getZoomNode();
	
	
}
