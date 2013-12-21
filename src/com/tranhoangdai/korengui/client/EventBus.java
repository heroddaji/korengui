package com.tranhoangdai.korengui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.controller.GUIController;
import com.tranhoangdai.korengui.client.controller.GlobalTopologyEvenController;
import com.tranhoangdai.korengui.client.controller.PathFlowEventController;
import com.tranhoangdai.korengui.client.controller.ZoomEventController;

public class EventBus {

	public enum ActionState {
		NOTHING, GLOBAL, ZOOM, FLOW1, FLOW2
	}

	public static EventBus INSTANCE = GWT.create(EventBus.class);
	boolean networkDownloaded = false;
	private ActionState state = ActionState.NOTHING;

	private EventBus() {
	}

	public void deliverDownloadGlobalTopologyEvent(Object source) {
		GUIController.INSTANCE.tellGlobalNetworkInstruction();
		GlobalTopologyEvenController.INSTANCE.handleEvent(source);

		networkDownloaded = true;
	}

	public void deliverGetPathFlowEvent(Object source) {
		if (!networkDownloaded) {
			GUIController.INSTANCE.tellDependentAction();
			return;
		}

		GUIController.INSTANCE.tellPathFlowAction1();
		state = ActionState.FLOW1;
		/*
		 * now wait for user to click on a node, the click event will be sent
		 * back to this class under function deliverEventUserClickedNode
		 */
	}

	public void deliverEventUserClickedZoomButton(Object source) {
		if (!networkDownloaded) {
			GUIController.INSTANCE.tellDependentAction();
			return;
		}
		GUIController.INSTANCE.tellZoomInstruction();
		state = ActionState.ZOOM;
		/*
		 * now wait for user to click on a node, the click event will be sent
		 * back to this class under function deliverEventUserClickedNode
		 */
	}

	public void deliverEventUserClickedNode(Object source) {

		if (state == ActionState.ZOOM) {
			ZoomEventController.INSTANCE.handleEvent(source);
			state = ActionState.NOTHING;
			GUIController.INSTANCE.clear();
		}

		//click first node		
		if (state == ActionState.FLOW1) {
			GUIController.INSTANCE.tellPathFlowAction2();
			PathFlowEventController.INSTANCE.handleEvent(source);
			state = ActionState.FLOW2;
		}

		//click second node
		else if (state == ActionState.FLOW2) {
			PathFlowEventController.INSTANCE.handleEvent(source);
			state = ActionState.NOTHING;
			GUIController.INSTANCE.clear();
		}
	}

	public void deliverEventUserSwitchPanelTab(Integer tabNumber) {
		GUIController.INSTANCE.switchTabsInline(tabNumber);
	}

	public void deliverEventUserClickNewMenu() {
		GUIController.INSTANCE.refreshWebApp();
	}

	public void deliverEventUserClickAboutMenu() {
		GUIController.INSTANCE.showAboutDiablog();
	}
	
	public void deliverEventUserClickCloseTabContextMenu(Widget tab) {
		GUIController.INSTANCE.closeTab(tab);
	}

	public ActionState getState() {
		return state;
	}

}
