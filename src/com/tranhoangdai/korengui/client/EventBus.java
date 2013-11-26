package com.tranhoangdai.korengui.client;

import com.google.gwt.core.client.GWT;
import com.tranhoangdai.korengui.client.controller.GlobalTopologyEvenController;
import com.tranhoangdai.korengui.client.model.Switch;

public class EventBus {
	
	public static EventBus INSTANCE = GWT.create(EventBus.class);
	private EventBus(){}	

	public void deliverDownloadGlobalTopologyEvent(Object source){
		GlobalTopologyEvenController.INSTANCE.handleEvent(source);
	}
	
	public void deliverZoomEvent(Object source){		
	}
	
	public void deliverGetPathFlowEvent(Object source){
		
	}
	
}
