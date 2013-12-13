package com.tranhoangdai.korengui.client.controller;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.tranhoangdai.korengui.client.EventBus;
import com.tranhoangdai.korengui.client.EventBus.ActionState;
import com.tranhoangdai.korengui.client.model.ModelWithId;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.SvgPanel;
import com.tranhoangdai.korengui.client.view.svg.AbstractElementSvg;

public class PathFlowEventController extends AbstractEventController {

	public static PathFlowEventController INSTANCE = GWT.create(PathFlowEventController.class);

	ModelWithId model1 = null;
	ModelWithId model2 = null;

	@Override
	public void handleEvent(Object source) {
		AbstractElementSvg clickedSvg = (AbstractElementSvg) source;

		if (EventBus.INSTANCE.getState() == ActionState.FLOW1) {
			Log.debug("clicked first node");
			model1 = clickedSvg.getModel();
		}

		if (EventBus.INSTANCE.getState() == ActionState.FLOW2 && model1 != null) {
			Log.debug("clicked second node");
			model2 = clickedSvg.getModel();
			
			performAction();
		}
	}
	
	private void performAction(){
		SvgPanel.INSTANCE.drawPathFlow(model1, model2);
		InfoPanel.INSTANCE.drawPathFlow(model1, model2);
	}

}
