package com.tranhoangdai.korengui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.controller.GUIController;
import com.tranhoangdai.korengui.client.controller.GlobalTopologyEvenController;
import com.tranhoangdai.korengui.client.controller.PathFlowEventController;
import com.tranhoangdai.korengui.client.controller.ZoomEventController;

public class EventBus {

	public enum ActionState {
		NOTHING, GLOBAL, ZOOM, FLOW1, FLOW2
	}

	boolean networkDownloaded = false;
	private ActionState state = ActionState.NOTHING;

	GUIController guiController = null;

	public EventBus() {
		 guiController = new GUIController();
	}

	public void deliverDownloadGlobalTopologyEvent(final Object source) {
		guiController.showLoading();
		Korengui.INSTANCE.getGlobalTopologyEvenController().handleEvent(source);
		networkDownloaded = true;
		guiController.closeLoading();
	}

	public void deliverGetPathFlowEvent(Object source) {
		if (!networkDownloaded) {
			guiController.tellDependentAction();
			return;
		}

		guiController.tellPathFlowAction1();
		state = ActionState.FLOW1;
		/*
		 * now wait for user to click on a node, the click event will be sent
		 * back to this class under function deliverEventUserClickedNode
		 */
	}

	public void deliverEventUserClickedZoomButton(Object source) {
		if (!networkDownloaded) {
			guiController.tellDependentAction();
			return;
		}
		guiController.tellZoomInstruction();
		state = ActionState.ZOOM;
		/*
		 * now wait for user to click on a node, the click event will be sent
		 * back to this class under function deliverEventUserClickedNode
		 */
	}

	public void deliverEventUserClickedNode(Object source) {

		if (state == ActionState.ZOOM) {
			Korengui.INSTANCE.getZoomEventController().handleEvent(source);
			state = ActionState.NOTHING;
			guiController.clear();
		}

		//click first node
		if (state == ActionState.FLOW1) {
			guiController.tellPathFlowAction2();
			Korengui.INSTANCE.getPathFlowEventController().handleEvent(source);
			state = ActionState.FLOW2;
		}

		//click second node
		else if (state == ActionState.FLOW2) {
			Korengui.INSTANCE.getPathFlowEventController().handleEvent(source);
			state = ActionState.NOTHING;
			guiController.clear();
		}
	}

	public void deliverEventUserSwitchPanelTab(Integer tabNumber) {
		guiController.switchTabsInline(tabNumber);
	}


	public void deliverEventUserClickCloseTabContextMenu(Widget tab) {
		guiController.closeTab(tab);
	}

	public ActionState getState() {
		return state;
	}

}
