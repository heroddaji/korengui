package com.tranhoangdai.korengui.client;

import com.google.gwt.core.client.GWT;
import com.tranhoangdai.korengui.client.controller.GUIInstructionController;
import com.tranhoangdai.korengui.client.controller.GlobalTopologyEvenController;
import com.tranhoangdai.korengui.client.controller.ZoomEventController;
import com.tranhoangdai.korengui.client.model.Switch;

public class EventBus {
	
	public enum ActionState {
		NOTHING, GLOBAL, ZOOM, FLOW
	}
	
	public static EventBus INSTANCE = GWT.create(EventBus.class);
	boolean networkDownloaded = false;
	ActionState state = ActionState.NOTHING;
	
	
	private EventBus(){}	

	public void deliverDownloadGlobalTopologyEvent(Object source){
		GUIInstructionController.INSTANCE.tellGlobalNetworkInstruction();
		GlobalTopologyEvenController.INSTANCE.handleEvent(source);
		
		networkDownloaded = true;
	}
	
	public void deliverEventUserClickedZoomButton(Object source){
		if(!networkDownloaded){
			GUIInstructionController.INSTANCE.tellDependentAction();
			return;
		}
		GUIInstructionController.INSTANCE.tellZoomInstruction();	
		state = ActionState.ZOOM;
		/* 
		 * now  wait for user to click on a node, the click event will be
		 * sent back to this class
		 * under function deliverEventUserClickedNode
		 */
	}
	
	public void deliverEventUserClickedNode(Object source){
		if(state == ActionState.ZOOM){
			ZoomEventController.INSTANCE.handleEvent(source);
			state = ActionState.NOTHING;
			GUIInstructionController.INSTANCE.clear();
		}
	}
	
	public void deliverGetPathFlowEvent(Object source){
		if(!networkDownloaded){
			GUIInstructionController.INSTANCE.tellDependentAction();
			return;
		}
	}
	
}
