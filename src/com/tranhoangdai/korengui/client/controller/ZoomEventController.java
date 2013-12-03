package com.tranhoangdai.korengui.client.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.tranhoangdai.korengui.client.model.AttachmentPoint;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.model.util.ModelHelper;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.SvgPanel;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;

public class ZoomEventController extends AbstractEventController {

	public static ZoomEventController INSTANCE = GWT.create(ZoomEventController.class);

	@Override
	public void handleEvent(Object source) {
		NodeSvg zoomNodeSvg = (NodeSvg) source;
		Switch zoomSwitchModel = (Switch) zoomNodeSvg.getModel();		
		
		Map<String, Host> childHosts = ModelHelper.getChildHostsOfSourceSwitch(zoomSwitchModel, ClientServiceHelper.INSTANCE.getTopologyHosts());
		if(childHosts.size() == 0){
			GUIInstructionController.INSTANCE.tellConnectedHostsToZoomIn();
			return;
		}
		SvgPanel.INSTANCE.drawZoomTopology(zoomSwitchModel,childHosts);		
		InfoPanel.INSTANCE.showZoomTopology(zoomSwitchModel,childHosts);
	}
	
	
	

}
